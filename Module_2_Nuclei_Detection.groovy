// Before running the script, change the directory folder with the one were your 'he_heavy_augmented.pb' model is located.

def modelPath = "C:\\Users\\Giorgio Cazzaniga\\AppData\\Local\\QuPath-0.4.3\\extensions\\extensions\\he_heavy_augment.pb"

import qupath.lib.gui.dialogs.Dialogs
import qupath.lib.objects.PathAnnotationObject
import groovy.lang.GroovyShell
import qupath.ext.stardist.StarDist2D
import qupath.lib.gui.dialogs.Dialogs
import qupath.lib.scripting.QP

Dialogs.showMessageDialog("Module 2: Nuclei Detection", "Dear User,\n\nIn this module, I will guide you into nuclei detection through the Stardist extension\n\nBefore you begin, please make sure you have annotated the area to macro/micro dissect")

def qupath = QuPathGUI.getInstance()

def viewer = qupath.getViewer()
def selectedObject = viewer.getSelectedObject()

if (selectedObject instanceof PathAnnotationObject) {
    PathAnnotationObject annotation = (PathAnnotationObject) selectedObject
    annotation.setName("Dissection Area")
    viewer.repaint()
    println("Annotation name changed to: " + annotation.getName())
} else {
    println("The selected object is not an annotation.")
}

def stardist = StarDist2D
    .builder(modelPath)
    .normalizePercentiles(0.2, 99.8) // Percentile normalization
    .threshold(0.2)              // Probability (detection) threshold
    .pixelSize(0.5)              // Resolution for detection
    //.createAnnotations()         // Generate annotation objects using StarDist, rather than detection objects           
    //.cellConstrainScale(1.5)     // Constrain cell expansion using nucleus size
    .ignoreCellOverlaps(false)   // Set to true if you don't care if cells expand into one another
    .constrainToParent(false)    // Prevent nuclei/cells expanding beyond any parent annotations (default is true)
    .simplify(0.5)                 // Control how polygons are 'simplified' to remove unnecessary vertices
    //.includeProbability(true)    // Add probability as a measurement (enables later filtering)
    .measureShape()              // Add shape measurements
    .measureIntensity()          // Add cell measurements (in all compartments)
    .build()

def pathObjects = QP.getAnnotationObjects()
def imageData = QP.getCurrentImageData()
if (pathObjects.isEmpty()) {
    QP.getLogger().error("No parent objects are selected!")
    return
}
stardist.detectObjects(imageData, pathObjects)
stardist.close() // This can help clean up & regain memory
println('Done!')

runPlugin('qupath.lib.algorithms.IntensityFeaturesPlugin', '{"pixelSizeMicrons":2.0,"region":"ROI","tileSizeMicrons":25.0,"colorOD":true,"colorStain1":true,"colorStain2":true,"colorStain3":true,"colorRed":true,"colorGreen":true,"colorBlue":true,"colorHue":true,"colorSaturation":true,"colorBrightness":true,"doMean":true,"doStdDev":true,"doMinMax":true,"doMedian":true,"doHaralick":true,"haralickDistance":1,"haralickBins":32}')

Dialogs.showMessageDialog("Annotate tumor area", "Before running the last module, create a 'Tumor' class, an 'Other' class and an 'Ignore' class.\n\nThen, make 5-10 annotations per 'Tumor' and 'Other' classes with 5 nuclei each and annotate everything that is not a nucleus into 'Ignore' class.\n\nThen press CTRL+ALT+D and directly click 'Live Update' in the Object Classifier")