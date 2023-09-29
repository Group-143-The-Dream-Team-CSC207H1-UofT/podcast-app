# CSC207 Course Project: Podcast Application

An application made in Java using Clean Architecture for CSC207 at University of Toronto.

## Tenative Problem Domain
Tenatively, we are considering media management as a scope for our project. Specifically, 
this includes accessing media available on the web and managing various audio content.

## Project Overview
We are hoping to develop an application that allows a users to re-experience podcasts that
are available on sources such as Spodify, Apple Podcasts, Google Podcasts etc. We are hoping
to do this by:
1. Trying to incorporate voice-to-text/transcription into the application so that you can also read what is being said.
2. Implementing a feature that allows users to select sections of the podcast to listen to.
    - This essentially offers a summary of the different topics that are discussed within the podcast.
3. Potentially adding a search menu that can look for certain phrases/sentences within the podcast as well.


Note: The following list of features is tenative and subject to change.

## API Overview 

### Documentation

OpenAI Whisper Model Transcription API: https://platform.openai.com/docs/api-reference/audio/createTranscription

### API Tools & Example

Example API call using Hoppscotch:
![api_example_request.png](src/main/resources/api_example_request.png)

Example output from java code:
```
I keep seeing shorts in this channel like come on guys come on we're real men here
```

## Note To Readers
Information on this file is subject to change, especially when the project will be updated. 
