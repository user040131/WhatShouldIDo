# 🧪 WhatShouldIDo

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-%23005F0F.svg?style=for-the-badge&logo=thymeleaf&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
![AI Assisted](https://img.shields.io/badge/AI%20Assisted-ChatGPT%20%7C%20Gemini-74aa9c?style=for-the-badge)

> **"An AI-assisted playground for implementing various web features without constraints."** > **형식에 구애받지 않고, AI와 협업하여 상상하는 모든 기능을 구현하는 실험적 웹 플랫폼.**

<br>

## 🚀 Project Concept

**WhatShouldIDo**는 단순한 단일 서비스를 넘어선 **'All-in-One' 개발 놀이터**입니다.  
개발자가 흥미를 느끼는 다양한 기능(Feature)들을 **LLM(Large Language Model)**과 적극적으로 협업하여 빠르게 프로토타이핑하고 고도화하는 것을 목표로 합니다.

- **Unconstrained**: 특정 도메인에 얽매이지 않는 자유로운 기능 확장
- **AI-Powered**: 기획부터 코드 리팩토링까지 AI를 페어 프로그래머로 활용
- **Scalable**: 영화 추천을 시작으로, 일상 유틸리티부터 복잡한 로직까지 무한 확장

<br>

## 🧩 Feature Modules

현재 구현된 기능과 앞으로 추가될 기능들을 모듈별로 관리합니다.

| Status | Module             | Description                                                  | Key Tech                             |
| :---: |:-------------------|:-------------------------------------------------------------|:-------------------------------------|
| ✅ | **영화 추천**          | **취향 분석 영화 추천기**<br>사용자가 선택한 영화(5+)의 벡터를 분석하여 TMDB 기반 추천 제공  | `Vector Similarity` `TMDB API` `JPA` |
| ✅ | **오늘 뭐 먹지?**       | **랜덤 음식 추천기**<br>먹는 시각만 얘기해주면 고민 끝!                          | `Math.random`                        |
| ✅ | **컬러 테라피**         | **물결치는 3차원 구체를 바라보며 명상하기**<br>자극이 넘치는 인터넷에서 잠시 누리는 테라피       | `Three.js` `GLSL`                    |
| ✅ | **로또 번호**          | **클릭 한 번에 노려보는 인생 역전*<br>LinkedHashSet을 통해 안정적으로 동작하는 로또 추첨기 | `LinkedHashSet`                      |
| ✅ | **AI 챗봇**          | **로컬에서 무료로 동작하는 ai!**<br>열심히 사용해서 chatgpt에게 경제보복 이룩하기        | `AJAX` `Ollama`                      |
| 🚧 | **여행지 추천**         | *상황, 성형등을 입력하면 자동으로 추천되는 당신만의 힐링 스팟*                         | *TBD*                                |
| 🚧 | **(Next Feature)** | *여기에 새로운 아이디어를 적으세요*                                         | *TBD*                                |
| 🗓️ | **(Planned)**      | *추후 구현 예정 기능*                                                | *TBD*                                |

> **Status Guide**: ✅ Completed | 🚧 In Progress | 🗓️ Planned

<br>

## 🛠 Tech Stack

### Backend
- **Core**: Java 17, Spring Boot 3.x
- **ORM**: Spring Data JPA, Hibernate
- **DB**: MySQL 

### Frontend
- **Engine**: Thymeleaf
- **Styling**: Vanilla CSS, FontAwesome

### AI & Tools
- **Collaboration**: ChatGPT, Gemini (Code Generation & Refactoring)
- **API**: TMDB (The Movie Database)

<br>

## 📸 Screenshots

|    Analysis    |   Screenshots   |
|:--------------:|:---------------:|
| *(이미지에 대한 설명)* | *(스크린샷 이미지 링크)* |
|      *설명*      |      *화면*       |

<br>

## 🤝 Contribution
이 프로젝트는 개인의 학습과 실험을 위한 공간이지만, 재미있는 아이디어가 있다면 언제든 환영합니다.