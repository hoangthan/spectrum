## Architecture
- Apply modularization, divide the app to small part called 'module' to be reuse easily. Each part is a module. Each module is independent and serves a clear purpose
- Apply clean architecture in every module to make the source code easy to read, maintain
- MVVM in presentation layer (features module). Loose coupling from the View and Logic and separate the logic
- The libraries module will contain the child modules handle business logic and return pure data
- The features module will implement the UI logic and use the libraries module to handle logic

## Demo video
[![Demo](https://img.youtube.com/vi/SFfDzA-XgE0/0.jpg)](https://www.youtube.com/watch?v=SFfDzA-XgE0 "Demo")
