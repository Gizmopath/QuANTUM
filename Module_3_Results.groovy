import qupath.lib.gui.dialogs.Dialogs
import qupath.lib.objects.PathAnnotationObject
import groovy.lang.GroovyShell
import qupath.lib.gui.QuPathGUI
import qupath.lib.gui.measure.ObservableMeasurementTableData

Dialogs.showMessageDialog("Module 3: Results", "Dear User,\n\nThe result is finally ready! Click 'OK' to visualize it.")

def imageData = getCurrentImageData()
def annotations = getAnnotationObjects()
def ob = new ObservableMeasurementTableData()
ob.setImageData(imageData, annotations)


def detKey = "Num Detections"
def tumorKey = "Num Tumor"

def dissectionArea = annotations.find { it.getName() == "Dissection Area" }

if (dissectionArea) {    
    def numDetections = ob.getNumericValue(dissectionArea, detKey)
    def numTumor = ob.getNumericValue(dissectionArea, tumorKey)
    
    if (numDetections != null && numTumor != null) {
        // Calculate tumor cellularity
        def tumorCellularity = (numTumor / numDetections) * 100
        def resultString = String.format("%.2f%%", tumorCellularity)
        
        Dialogs.showMessageDialog("Tumor cellularity", "Tumor cellularity for 'Dissection Area': " + resultString)
    } else {
        println("Could not find required measurements for 'Dissection Area'.")
    }
} else {
    println("Annotation object 'Dissection Area' not found.")
}

