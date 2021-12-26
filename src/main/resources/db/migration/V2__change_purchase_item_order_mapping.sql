alter table purchase_item_table
    add order_id int not null;

alter table purchase_item_table
    add constraint purchase_item_table_order_table_id_fk
        foreign key (order_id) references order_table;