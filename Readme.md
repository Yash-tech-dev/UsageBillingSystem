# Resource Usage & Billing System

## Overview

Resource Usage & Billing System is a Core Java console-based application that manages limited-capacity resources and calculates billing based on the duration of resource usage.

The system allows users to start and stop resource usage, checks resource capacity, tracks usage time, and generates a bill according to hourly pricing rules.

## Features

- Manage resources with limited capacity
- Associate services with resources
- Start resource usage
- Stop resource usage
- Track start and end time
- Check resource availability
- Reject usage when resource capacity is full
- Calculate usage duration
- Round partial hours upward
- Apply first-hour pricing
- Apply additional-hour pricing
- Generate final bill in INR
- Console-based interaction

## Technologies Used

- Java
- Core Java
- ArrayList
- LocalDateTime
- Duration
- IntelliJ IDEA

## Project Structure

```text
src
└──--
    ├── User.java
    ├── Resource.java
    ├── Service.java
    ├── Usage.java
    ├── Bill.java
    ├── BillingCalculator.java
    ├── HourlyBillingCalculator.java
    ├── UsageManager.java
    └── Main.java

```text

## Billing Rules

The billing system follows an hourly pricing model.

For example:

First hour: ₹30
Each additional hour: ₹10

Partial hours are rounded upward.

Example:

Usage Duration = 1 hour 20 minutes

Billable Hours = 2 hours

First Hour = ₹30
Additional Hour = ₹10

Total = ₹40

Example:

Usage Duration = 1 hour 20 minutes

Billable Hours = 2 hours

First Hour = ₹30
Additional Hour = ₹10

Total = ₹40
```

Capacity Management

Each resource has a defined maximum capacity.

For example:

Meeting Room
Capacity = 2 users

If two users are already using the resource, a third usage request will be rejected.

When an existing user stops using the resource, the capacity becomes available again.

Usage Flow
User
|
v
Select Resource
|
v
Check Capacity
|
+---- Full ----> Reject Request
|
v
Start Usage
|
v
Record Start Time
|
v
Stop Usage
|
v
Record End Time
|
v
Calculate Duration
|
v
Calculate Bill
|
v


Generate Final Bill
How to Run
Clone the repository.
Open the project in IntelliJ IDEA.
Make sure Java is installed.

Run:
Main.java
Follow the instructions displayed in the console.
Example
====================================
RESOURCE USAGE & BILLING
====================================

Available Resources:

1. Meeting Room | Capacity: 2
2. Gym Machine | Capacity: 1

Enter User ID: 101
Enter Resource ID: 1
Enter Service ID: 101

Usage started successfully.

Session ID : 1
Start Time : 2026-10-01T10:00

Press ENTER when you want to stop usage...

================================
FINAL BILL
================================
User ID       : 101
Resource      : Meeting Room
Billable Hours: 2
--------------------------------
First Hour    : ₹30.0
Additional    : ₹10.0
--------------------------------
Total Amount  : ₹40.0
================================


Handles in-memory storage:

Resource
Service
UsageSession
Usage
Bill

Contains the main business logic:

Capacity validation
Start usage
Stop usage
Duration calculation
Billing calculation
Main



