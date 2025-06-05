# TODO List

## Necessary Services to Create

- [ ] payment
  - [ ] payment via google pay
  - [ ] payment via apple pay 
  - [ ] payment via POS terminal
  - [ ] payment via QR code
- order
  - [ ] order to tables
  - [ ] order from central pos device
  - [ ] order from waiter screens
  - [ ] kitchen monitor for orders
- layout 
  - [x] basic layouts
  - [ ] flexible layouts with positions
  - [ ] styled configs for layouts
- [ ] menu
  - [x] multiple menus
  - [x] menu categories and their position on UI
  - [x] menu items and their position on UI
  - [ ] images of menu items and categories
- [ ] inventory
  - [x] inventory purchases
  - [x] inventory transactions (logs)
  - [x] inventory dispatches
  - [ ] multi role inventory management
  - [ ] filters such as category, price, amount for inventory items, dispatches, transactions
- [ ] notification
  - [ ] alerts for low stock, orders, payment request.
  - [ ] specialized notifications for each staff member.
  - [ ] payment notifications for users who paid check with QR code.
- [ ] loyalty
  - [ ] loyalty for customers.
  - [ ] campaign messages via Telegram or WhatsApp to customers.

## Business Notes

## Code Notes

- use soft delete in appropriate entities.
- add log statements after changes in database. For example, adding, deleting, or updating a record.
- add validation and swagger for controller layers. 
