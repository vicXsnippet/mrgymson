# FitSync: Enhancing Home Exercise with Smart Tracking
## Product Capstone

FitSync app was created as an innovative solution. FitSync offers a range of home exercise programs tailored to users' fitness levels, complemented by animations that provide visual instructions on how to perform each movement correctly. This advanced feature of the app includes movement quality assessment that gives real-time feedback, ensuring that each repetition is counted accurately if the movement is performed well and correctly. The app also features a reminder function that encourages consistency in workouts, as well as an interactive dashboard to track progress, such as workout duration and calories burned. By focusing on the accuracy of movements and a personalized user experience, FitSync aims to not only support individual fitness but also promote a sustainable healthy lifestyle in the home environment.

## Application Design

Check out the [Figma design](https://www.figma.com/design/WXPpRN7tNM97J5xs1qzAA6/FitsyncIOSAppDesign?node-id=0-1&t=o1VIjlqtnTOkdYKt-1)

- **Machine Learning**: Building models with TensorFlow Lite using MoveNet for pose feature extraction, presenting the model with data preprocessing, and processing the data to improve accuracy.

- **Mobile Development**: Developing the mobile app using Kotlin, deploying TensorFlow Lite for real-time pose detection, integrating data from APIs, and implementing the extracted pose features for user interaction and feedback.

- **Cloud Computing**: Building an API with Node.js and Hapi to communicate between the backend and mobile app using Cloud Firestore and Cloud Run for deployment API, as well as Cloud Storage to store user training images.

### API documentation link
[https://documenter.getpostman.com/view/30754097/2sA3XQhhhH](https://documenter.getpostman.com/view/30754097/2sA3XQhhhH)

### Link deploy API using Cloud Run
[https://fitsync-backend-fvljxqxrgq-et.a.run.app](https://fitsync-backend-fvljxqxrgq-et.a.run.app)

## How to Contribute

Thank you for your interest in contributing! To participate in the development of this project, please follow these steps:

1. Fork this repository.
2. Create or switch to a branch based on the learning path, feature, or improvement you desire:
   ```sh
   git checkout -b branch-name
3. Make the necessary changes to the code and commit them with a brief description of the changes made:
   ```sh
   git commit -m 'Brief description of changes
4. Push your changes to the new branch on your forked repository:
   ```sh
   git push origin branch-name
5. Submit a pull reques
   Go to the original repository on GitHub and click the "New Pull Request" button. Select your branch and submit the pull request to the main branch of this repository.
