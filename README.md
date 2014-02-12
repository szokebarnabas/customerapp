Customer register
=================

The purpose of this app is to integrate AngularJS and Spring MVC via RESTful Web Service and to learn the technologies listed below.

Technologies:

  - Client side
    - Bootstrap 3
    - AngularJS-Seed
    - AngularJS 1.2.9
    - ngTable 0.3.1
    - Spin.js for long running REST requests
    - HTTP Auth Interceptor Module
    
  - Server side
    - Spring Framework 3.2.0
    - Spring Security 3.2


Building
--------

<pre><code>mvn package</code></pre>

Running
-------

You can run the webapp in Jetty with <pre><code>mvn jetty:run</code></pre>

After it the app is available at http://localhost:8080

username: user<br/>
password: user

I used the following great project to integrate Spring Security with AngularJS:
https://github.com/jhipster/jhipster-sample-app
