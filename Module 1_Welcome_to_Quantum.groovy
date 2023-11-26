import qupath.lib.gui.dialogs.Dialogs
import qupath.lib.objects.PathAnnotationObject
import groovy.lang.GroovyShell

Dialogs.showMessageDialog("Welcome to QUANTUM!", "Dear User,\n\nI will guide you step by step through the execution of this script for automatic tumor cell counting.\n\nBefore you begin, please make sure you have installed the QuPath Stardist extension and downloaded the specific module for detecting nuclei in H&E images.")
def choices = ["Whole Slide", "Dissected Area"]
def defaultChoice = "Dissected Area"
def selectedChoice = Dialogs.showChoiceDialog("Choose an Option", "Select an option from the list:", choices, defaultChoice)
if (selectedChoice != null) {
    if (selectedChoice == "Whole Slide") {
        createFullImageAnnotation(true)
        Dialogs.showMessageDialog("Module 1", "Full Image Annotation performed correctly.\n\nMove to Module 2")
    } else {
        Dialogs.showMessageDialog("Module 1: Select the area to dissect", "Annotate the tumor area that will be macro- or micro-dissected before running Module 2.\n\nHint#1: You can select the annotation boundaries and automatically fill it by going to Object->Annotations->Fill Holes.\n\nHint#2: If the whole slide will be used for the molecular test, go to Objects->Annotations->Create full image annotation.\n\nAfter completing this first step, select the annotation and proceed with Module 2.")
    }
} else {
    println("Dialog canceled or closed.")
}
print("Done!")