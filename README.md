# Open Weather API App

This is a weather application that provides current weather data for a specified city. The app fetches weather information and displays it in a user-friendly interface.

## Features
- Displays current weather conditions (temperature, humidity, feels-like temperature, air pressure).
- Fetches data from a weather API.
- Includes a mock repository for testing in debug mode.

## Setup Instructions

### 1. Clone the Repository
Clone this repository to your local machine using:

```bash
git clone https://github.com/sisiame/openweatherapiapp.git
```

### 2. Open the Project
Open the project in Android Studio.

### 3. Setup gradle.properties File
To enable API key integration, you'll need to add your personal Weather API key to the `gradle.properties` file.

- Open the `gradle.properties` file located in the root of your project.
- Add your personal Weather API key like so:

```properties
weather_api_key=your_personal_api_key_here
```
Note: You can sign up for a free API key at WeatherAPI if you don’t have one.

### 4. Sync the Project
After adding the API key to `gradle.properties`, sync the project with Gradle. In Android Studio, click `File > Sync Project with Gradle Files` to ensure the `BuildConfig` class is updated with the new API key.

### 5. Using the app

- **Search for a City**: Start typing the name of a city in the search bar. As you type, the app will automatically display the weather for the matching city.

- **View Weather for a City**: Once a city is found, click on the city card to view detailed weather information on the home screen. The weather data will be updated for the selected city.

- **Location Permissions**: On the first launch, the app will request location permissions. If granted, the app will fetch and display the weather data for your current location, and automatically save this city locally for future use.

- **Saving Cities**: The app saves the weather data of cities you click on, allowing you to quickly view and access the weather information for those cities later. If your location is accessed during the first launch, your current location’s weather will also be saved.

<img src="https://drive.google.com/uc?export=view&id=1P1U8VFBjLAN-vnLzSMfEok3mdyavQsk0" width="300"> <img src="https://drive.google.com/uc?export=view&id=1P3lWoN9xujXPyU9WJopAoC5Zwg0uCW-y" width="300"> <img src="https://drive.google.com/uc?export=view&id=1P22dyzec8bHQTNzpT0g3MNpXg5S50dkC" width="300">

### 6. Build and Run the App
Once you've configured the API key and selected your desired repository, you can build and run the app in Android Studio by clicking `Run` or using the shortcut `Shift + F10`.

### 7. Testing
You can now test the app by entering the name of a city, and the app will fetch and display the weather data. If you are running the app in debug mode, the mock data will be displayed by default.

**Note**: When using real API calls, ensure that your device or emulator has an active internet connection.
