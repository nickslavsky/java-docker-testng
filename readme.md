This is a fully working PoC project for running Selenium Tests in Docker. 

The test opens up Google page in the latest version of Chrome on Win 10, captures all the POST requests the page sends back and prints their URLs


Key highlights:

- Browsermob proxy is used to intercept all the requests
- Sauce Labs is used to run the tests in the cloud (you need to put your valid credentials into the Dockerfile)
- Sauce Connect proxy tool is used to run the traffic from Sauce Labs cloud through the local BrowserMob proxy instance
- `browsermob.js` file is used to configure Sauce Connect
- The tests are run inside a Docker container
- `RUN ["mvn", "verify", "clean", "--fail-never"]` in Dockerfile is need to ensure you cache all the downloaded dependencies