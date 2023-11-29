# QuANTUM - Qupath Analysis of Nuclei from Tumor to Uniform Molecular tests ![DALL·E 2023-11-21 13 50 48 - Create a minimalistic logo with a more defined and clear representation of a DNA double helix forming the letter 'Q'  The helix should be depicted in ](https://github.com/Gizmopath/QuANTUM/assets/119873860/afc1bd45-90e2-4d84-887f-285a92b82e4a)

###Computational Pipeline for Tumor Cellularity Detection in Pulmonary Pathology

Welcome to the QuANTUM repository, a computational pipeline designed for the detection of tumor cellularity in molecular pathology investigations of lung tissues. This pipeline, composed of three Groovy scripts, seamlessly integrates with QuPath, utilizing the 'Stardist' extension and QuPath's built-in object classifier for the detection and classification of lung carcinoma nuclei. Follow the steps below to achieve accurate results.

## Prerequisites:

Ensure you have the following prerequisites installed before running QuANTUM:

- **QuPath Version:** 0.4.4
  [QuPath 0.4.4 Download](https://qupath.github.io/)
  
- **Stardist Extension:**
  - [Stardist GitHub](https://github.com/qupath/qupath-extension-stardist)
  - [Stardist Documentation](https://qupath.readthedocs.io/en/0.3/docs/advanced/stardist.html)

- **Nuclei Detection Module:**
  - Download the nuclei detection model: [he_heavy_augment.pb](https://github.com/qupath/models/tree/main/stardist)

## Usage Instructions:

1. Open QuPath and create a project with the target image for analysis. In the 'Annotations' section, create three classes: 'Tumor', 'Other', and 'Ignore'.
![MAin viewer](https://github.com/Gizmopath/QuANTUM/assets/119873860/a5084c88-8593-4689-b964-8c957df3ccc2)

2. Drag and drop the first module into the QuPath window, then click 'Run'. Follow the on-screen instructions. If the annotation of the entire slide is not automatically selected, annotate the area for molecular analysis manually.
<img width="1920" alt="Module 1" src="https://github.com/Gizmopath/QuANTUM/assets/119873860/6e5b4dcd-81d6-489f-88a5-aff4bd343ae2">
<img width="1920" alt="REsection area" src="https://github.com/Gizmopath/QuANTUM/assets/119873860/f8a5f6e8-662b-439f-8714-94d73686fe41">

3. Drag the second module and execute the automated steps. Make sure you have changed the stardist module directory to your actual directory.
  ![Module 2 cambia dir-min](https://github.com/Gizmopath/QuANTUM/assets/119873860/111b71a6-8dd8-4866-8432-fee16ff174e5)

4. After module 2 completion, make 5-10 annotations for 'Tumor' and 'Other' categories, each containing 5 nuclei. If needed, label incorrect detections, including artifacts (e.g., anthracosis, tissue folding), as 'Ignore'.
  <img width="298" alt="specchietto annotazioni" src="https://github.com/Gizmopath/QuANTUM/assets/119873860/2f6745e5-c874-421f-a2bd-162842b7c058">


5. Open the 'Train Object Classifier' window (Classify -> Object Classifier -> Train Object Classifier), press 'Live Update', close the window.
  ![Classifier-min](https://github.com/Gizmopath/QuANTUM/assets/119873860/a9f97678-1eed-46d3-90e9-d5f9f861be05)

6. Drag the third module. This module will display the percentage of cellularity in the examined sample.

## Important Links:

- [QuANTUM Article]

## Intellectual Property Statement:

QuANTUM is the intellectual property of Vincenzo L'Imperio and Giorgio Cazzaniga.

Feel free to explore, contribute, and enhance QuANTUM for the advancement of pulmonary pathology research!

Happy Analyzing!
