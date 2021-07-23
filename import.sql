insert into ROLES (role_id, role_name) values (1, 'ADMIN')
insert into ROLES (role_id, role_name) values (2, 'CUSTOMER')

insert into CATEGORY (category_id, category_name, "description") values (1, "Clothing", "Stuff that made from fabric")
insert into CATEGORY (category_id, category_name, "description") values (2, "Electronic", "Stuff that use electricity")
insert into CATEGORY (category_id, category_name, "description") values (3, "Food", "Stuff that can be eaten")

insert into PRODUCT (id, product_name, product_description, img_url, price, unit, created_by, created_date, updated_date) 
    values (1, 'Rau qua', 'Rau cu tuoi', 'http://via.placeholder.com/1024x768', 100000, 'ki', 1, '2021-22-07','2021-22-07')