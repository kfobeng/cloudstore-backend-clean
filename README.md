# CloudStore Backend – Standalone API

This repository contains the standalone backend logic for CloudStore – a cloud storage platform designed with user-centric features and local payment integration for Ghana and beyond.

## 🔧 Features
- 💸 **Mobile Money Integration**
  - Uses Flutterwave API
  - Supports secure MoMo payments for storage upgrades

- 📈 **Dynamic Storage Upgrade**
  - Controller + Service logic to handle user tier upgrades
  - Includes DTOs for clean request handling

- 📂 **User Model Enhancements**
  - Support for payment status and plan management

## ⚙️ Tech Stack
- Java 17
- Spring Boot
- Maven
- RESTful API principles

## 🧪 Endpoints
- `POST /pay/storage` – Trigger MoMo payment
- `POST /upgrade` – Upgrade user storage
- `GET /user/{id}` – Retrieve user info

## 🛠 Structure
