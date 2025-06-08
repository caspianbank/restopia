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
  - [ ] add tenantCode to appropriate entities
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
- [ ] audit events - additional charge for businesses.
  - [ ] keep track of every action on business system. e.g. Jack deleted task 1. Table 1 ordered meal 3.
  - [ ] SSE to send to Business Admin Panel to see latest changes. 

## Business Notes

## Code Notes

- use soft delete in appropriate entities.
- add log statements after changes in database. For example, adding, deleting, or updating a record.
- add validation and swagger for controller layers. 
- develop CRON jobs in Quarkus
- check performance of SQL query before writing it via `explain analyze`
- create necessary DB indexes based on conditions and filters.
- database components must begin with the prefix followed by actual name 
  - fk_employee_id -> foreign key
  - uk_employee_email -> unique key
  - pk_employee_id -> primary key
  - ind_employee_name -> index
  - nnc_employee_name -> not null constraint
  - cc_employee_age -> check constraint
  - func_sum_of_employee_salary -> function
  - proc_update_transactions -> procedure
  - trg_insert_rules -> trigger
