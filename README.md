# Bus Resrvation System

<p style="text-align: justify;">This bus reservation system leverages core <b>Abstract Data Types (ADTs)</b> to effectively manage its operations. A queue ensures fair processing of customer requests whether it's for booking new reservations, changing existing seats, or canceling reservations by adhering to a <b>First-In, First-Out (FIFO)</b> structure. An <b>AVL</b> tree efficiently stores and retrieves both customer and bus information. The <b>AVL</b> tree's self-balancing nature ensures consistently fast performance for searching, adding, or removing data, which is crucial for a system with frequent updates. Seat allocation within each bus is managed dynamically using a linked list, accurately reflecting the real-world arrangement of seats.</p>

<p style="text-align: justify;">When the system starts, it retrieves customer, bus, and reservation data from the database and loads it into the <b>AVL</b> tree. This tree serves as the central data structure, where each node represents an object either a Customer, a Bus, or a Booking. These objects encapsulate the relevant data and provide methods for interacting with the system. Each object has a unique ID, simplifying the process of finding and managing specific data within the database. To maintain data integrity and prevent unauthorized access, certain functions are encapsulated, providing a controlled interface for interacting with the system. </p>

## Bus reservation System Diagram
![diagram](https://github.com/user-attachments/assets/9888170c-47a0-4b5c-bd07-f8109f22d5a6)
