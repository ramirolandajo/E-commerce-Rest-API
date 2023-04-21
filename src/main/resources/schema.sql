CREATE TABLE clients(
    id integer PRIMARY KEY AUTO_INCREMENT,
    name varchar(75) not null ,
    lastname varchar(75) not null ,
    docnumber varchar(11) unique not null
);

CREATE TABLE products(
    id integer PRIMARY KEY AUTO_INCREMENT,
    description varchar(150),
    code varchar(50) unique not null ,
    stock int,
    price double not null
);

CREATE TABLE invoices(
    id integer PRIMARY KEY AUTO_INCREMENT,
    client_id int,
    created_at date not null ,
    total double not null,
    constraint fk_client_id foreign key(client_id) references clients(id)
);

CREATE TABLE invoices_details(
    invoice_detail_id integer PRIMARY KEY AUTO_INCREMENT,
    invoice_id int,
    amount int not null ,
    product_id int,
    price double not null,
    constraint fk_invoice_id foreign key(invoice_id) references invoices(id),
    constraint fk_product_id foreign key(product_id) references products(id)
);

insert into clients(name, lastname, docnumber) values ('Ramiro', 'Landajo', '11111111'), ('Dibu','Martinez', '22222222');

insert into products(description, code, stock, price) values ('Control Remoto', '123', 50, 100), ('PlayStation 5', '567', 2, 2000);

insert into invoices(client_id, created_at, total) values (2, '2023-04-08', 2500), (1, '2023-04-09', 150);

insert into invoices_details(invoice_id, amount, product_id, price) values (1, 1, 2, 2000+500), (2, 1, 1, 100+50);

//para mostrar en la base de datos copiar y pegar en la consola de h2
select * from clients, products, invoices, invoices_details
where clients.id = invoices.client_id and invoices_details.invoice_detail_id = invoices.id and products.id = invoices_details.product_id;
