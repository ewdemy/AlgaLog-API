create table delivery(
                       id bigint not null auto_increment,
                       client_id bigint not null,
                       fee decimal(10,2) not null,
                       status varchar(20) not null,
                       ordered_date datetime not null,
                       end_date datetime,

                       addressee_name varchar(60) not null,
                       addressee_address varchar(255) not null,
                       addressee_number varchar(30) not null,
                       addressee_complement varchar(60),
                       addressee_district varchar(60) not null,
                       primary key (id)
);

alter table delivery add constraint fk_delivery_client foreign key (client_id) references client(id);