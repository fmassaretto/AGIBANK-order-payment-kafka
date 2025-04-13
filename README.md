<a id="readme-top"></a>
<!--
*** Thanks for checking out the AGIBANK order-payment with kafka. If you have a suggestion
*** that would make this better, please fork the repo and create a pull request
*** or simply open an issue with the tag "enhancement".
*** Don't forget to give the project a star!
*** Thanks again! Now go create something AMAZING! :D
-->



<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![Unlicense License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]



<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/fmassaretto/AGIBANK-order-payment-kafka">
    <img src="logo.png" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">Project Agibank - order-payment microservices with kafka</h3>

  <p align="center">
    An project for Agibank
    <br />
    <a href="https://github.com/fmassaretto/AGIBANK-order-payment-kafka"><strong>Explore the docs » (soon)</strong></a>
    <br />
    <br />
    <a href="https://github.com/fmassaretto/AGIBANK-order-payment-kafka">View Demo</a>
    &middot;
    <a href="https://github.com/fmassaretto/AGIBANK-order-payment-kafka/issues/new?labels=bug&template=bug-report---.md">Report Bug</a>
    &middot;
    <a href="https://github.com/fmassaretto/AGIBANK-order-payment-kafka/issues/new?labels=enhancement&template=feature-request---.md">Request Feature</a>
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#testing">Testing the application</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

This project is to demonstrate two microservices: order and payment communicating via Apache Kafka. 
The order service being responsible for create, list the orders and update the status of the orders 
when a payment is done. The payment service is responsible for when receive a payment confirmation 
should send a message kafka topic.


<p align="right">(<a href="#readme-top">back to top</a>)</p>



### Built With

This section should list any major frameworks/libraries used to bootstrap your project. Leave any add-ons/plugins for the acknowledgements section. Here are a few examples.

* [![Java][Java]][Java-url]
* [![Spring][Spring]][Spring-url]
* [![ApacheKafka][ApacheKafka]][ApacheKafka-url]
* [![Postgres][Postgres]][Postgres-url]
* [![Docker][Docker]][Docker-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

This is an example of how you may give instructions on setting up your project locally.
To get a local copy up and running follow these simple example steps.

### Prerequisites

For this project you will need these:
* Java 17 or above
* Docker Desktop
* Postgres 16
* Apache Kafka, that I recommend to use via docker (more in: https://kafka.apache.org/quickstart) if NOT running via docker-compose
  ```sh
  $ docker pull apache/kafka:4.0.0
  ```
  ```sh
  $ docker run -p 9092:9092 apache/kafka-native:4.0.0
  ```

### Installation

_Before go to usage section, do the following steps._

1. Clone the repo
   ```sh
   git clone https://github.com/fmassaretto/AGIBANK-order-payment-kafka.git
   ```
2. Go to orderservice directory and run:
   ```sh
   ./gradlew clean build
   ```
3. Go to paymentservice directory and run:
   ```sh
   ./gradlew clean build
   ```

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- USAGE EXAMPLES -->
## Usage

Use this space to show useful examples of how a project can be used. Additional screenshots, code examples and demos work well in this space. You may also link to more resources.

_For more examples, please refer to the [Documentation](https://example.com)_

### Method 1: Via IntelliJ/Terminal
1. Run OrderServiceApplication or via terminal:
    ```sh
   .\orderservice\gradlew bootRun
   ```
2. Run PaymentServiceApplication or via terminal:
    ```sh
   .\paymentservice\gradlew bootRun
   ```
3. Run the Apache Kafka as describe on Prerequisites

<p align="right">(<a href="#readme-top">back to top</a>)</p>



### Method 1: Docker (Recommended)
1. Run docker desktop
2. Run docker-compose:
    ```sh
   docker-compose up --build
   ```
3. The Order Service will be on port 8081
4. The Payment Service will be on port 8082

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- TESTING THE APPLICATION -->
## How to test the Application
_For now you can test using postman collections and follow these steps:_

1. Import Postman collection "AGIBANK-order-payment-kafka.postman_collection.json" from: https://github.com/fmassaretto/AGIBANK-order-payment-kafka/blob/main/AGIBANK-order-payment-kafka.postman_collection.json
2. After importing, uses the 'Create Order' request to create a new order
3. You can see the order by calling 'Get All Order' request
4. To simulate a payment call the 'Pay Order' to set the status to paid
5. You can confirm the status calling again the 'Get All Order' request

<!-- ROADMAP -->
## Roadmap
_Things to improve in future releases_

- [ ] Create two topics instead of one: for orders created and for orders paid
- [ ] Both microservices produce and consume for the topics
- [ ] Add Cache
- [ ] Add Swagger
- [ ] Add more tests
- [ ] Create a microservice only for kafka (TBD)

See the [open issues](https://github.com/fmassaretto/AGIBANK-order-payment-kafka/issues) for a full list of proposed features (and known issues).

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request



<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE.txt` for more information.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTACT -->
## Contact

Fabio Massaretto - [@fmassaretto](https://www.linkedin.com/in/fmassaretto/) - fmassaretto@gmail.com

Project Link: [https://github.com/fmassaretto/AGIBANK-order-payment-kafka](https://github.com/fmassaretto/AGIBANK-order-payment-kafka/e)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

Use this space to list resources you find helpful and would like to give credit to. I've included a few of my favorites to kick things off!

* [Choose an Open Source License](https://choosealicense.com)
* [GitHub Emoji Cheat Sheet](https://www.webpagefx.com/tools/emoji-cheat-sheet)
* [Malven's Flexbox Cheatsheet](https://flexbox.malven.co/)
* [Malven's Grid Cheatsheet](https://grid.malven.co/)
* [Img Shields](https://shields.io)
* [GitHub Pages](https://pages.github.com)
* [Font Awesome](https://fontawesome.com)
* [React Icons](https://react-icons.github.io/react-icons/search)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/othneildrew/Best-README-Template.svg?style=for-the-badge
[contributors-url]: https://github.com/fmassaretto/AGIBANK-order-payment-kafka/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/othneildrew/Best-README-Template.svg?style=for-the-badge
[forks-url]: https://github.com/fmassaretto/AGIBANK-order-payment-kafka/network/members
[stars-shield]: https://img.shields.io/github/stars/othneildrew/Best-README-Template.svg?style=for-the-badge
[stars-url]: https://github.com/fmassaretto/AGIBANK-order-payment-kafka/stargazers
[issues-shield]: https://img.shields.io/github/issues/othneildrew/Best-README-Template.svg?style=for-the-badge
[issues-url]: https://github.com/fmassaretto/AGIBANK-order-payment-kafka/issues
[license-shield]: https://img.shields.io/github/license/othneildrew/Best-README-Template.svg?style=for-the-badge
[license-url]: https://github.com/fmassaretto/AGIBANK-order-payment-kafka/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/fmassaretto/

[Java]: https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white
[Java-url]: https://www.java.com/en/
[Spring]: https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white
[Spring-url]: https://spring.io/
[Postgres]: https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white
[Postgres-url]: https://www.postgresql.org/
[ApacheKafka]: https://img.shields.io/badge/Apache%20Kafka-000?style=for-the-badge&logo=apachekafka
[ApacheKafka-url]: https://kafka.apache.org/
[Docker]: https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white
[Docker-url]: https://docker.com/