# Order Service

The Order Service is responsible for managing the lifecycle of customer food orders—from creation and item management to order status tracking and payment processing. It interacts with the User Service for user details and likely with the Menu Service for verifying product data (e.g., prices, availability).

This microservice encapsulates three key domains:

Orders

Order Items

Payments


✅Order Management

Create Order:
POST /api/orders/create
→ Creates a new order and associated items in one go.

Get All Orders by User:
GET /api/orders/getAll?userId=...
→ Lists all orders for a user.

Get Order by ID:
GET /api/orders/get?orderId=...
→ Fetches a single order with details.

Update Order Address:
PUT /api/orders/update
→ Allows updating the shipping address.

Update Order Status:
PUT /api/orders/updatestatus?orderId=...&orderStatus=...
→ Updates the order lifecycle stage (e.g., from PLACED to CONFIRMED).

🍽 Order Items Management

Add Items to Order:
POST /api/orderitems/add?orderId=...
→ Adds new food items to an existing order.

Update Item Quantity:
PUT /api/orderitems/update
→ Modifies quantity of an existing item.

Get All Items in Order:
GET /api/orderitems/getall?orderId=...

Get Item by ID:
GET /api/orderitems/get?orderItemId=...

Delete Item:
DELETE /api/orderitems/delete?orderItemId=...

💰 Payment Handling

Make Payment:
PUT /api/orderpayment/makepayment
→ Updates the payment status for an order (e.g., mark as completed).

Cancel Payment:
PUT /api/orderpayment/cancel?paymentId=...
→ Cancels a payment transaction.

Get Payment by Order:
GET /api/orderpayment/getbyOrder?orderId=...

Get Payment by Payment ID:
GET /api/orderpayment/getbyPaymentId?paymentId=...
