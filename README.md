# Real-Time Event Ticketing System

## Overview
The Real-Time Event Ticketing System simulates the distribution of event tickets through a dynamic system involving vendors and customers. Vendors release tickets to a shared pool, and customers retrieve tickets from this pool at a specified rate. The system is highly configurable and allows users to set parameters such as the number of tickets, ticket release rate, customer retrieval rate, and more.

This project involves multiple components including a ticket pool, vendor and customer threads, and a configuration manager. It also features file-based persistence, storing and loading configuration settings in JSON format.

## Features
- **Ticket Pool Management**: Tickets are stored in a shared pool where vendors release tickets and customers retrieve them.
- **Configuration Management**: Users can set and save the system configuration, which is stored in a JSON file.
- **Real-Time Simulation**: The system simulates the process of ticket release and retrieval, providing real-time updates.
- **Multi-Threaded Simulation**: The system utilizes threads to represent vendors and customers interacting with the ticket pool concurrently.
- **Customizable Rates**: Users can adjust ticket release rate, customer retrieval rate, and the number of vendors and customers.

## Components
1. **Configuration**: Manages system configuration settings and allows users to load/save settings to/from a JSON file.
2. **TicketPool**: A shared resource where tickets are stored and accessed by vendors and customers.
3. **Vendor**: A thread that releases tickets into the pool at a specified rate.
4. **Customer**: A thread that retrieves tickets from the pool at a specified rate.
5. **TicketingSystem**: Orchestrates the entire system by starting vendor and customer threads, managing the simulation flow.
6. **Main**: Provides a user interface for interacting with the system through a command-line menu.

## Setup and Installation

1. **Clone the Repository**:
   ```bash
   git clone <repository-url>
   cd <project-directory>
