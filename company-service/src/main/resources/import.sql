insert into country (id, code, name) values ('4f431d49-e07d-440c-9d73-a9ecac23c33a', 'SRB', 'Srbija');

insert into city (id, postal_code, name, country_id) values ('c869af79-d499-43d7-8697-bb77d3605d0a', '11000', 'Beograd', '4f431d49-e07d-440c-9d73-a9ecac23c33a');
insert into city (id, postal_code, name, country_id) values ('a57b3e3c-8c52-46f7-90f3-4d4b8fea04fb', '18000', 'Niš', '4f431d49-e07d-440c-9d73-a9ecac23c33a');
insert into city (id, postal_code, name, country_id) values ('eec398cc-c7a2-4159-9004-9f94d2ee1d8b', '21000', 'Novi Sad', '4f431d49-e07d-440c-9d73-a9ecac23c33a');
insert into city (id, postal_code, name, country_id) values ('1583206d-f318-4778-8cf9-485c76cb9a1e', '23000', 'Zrenjanin', '4f431d49-e07d-440c-9d73-a9ecac23c33a');
insert into city (id, postal_code, name, country_id) values ('b2c375ae-6e87-482d-966d-881374885abe', '25000', 'Sombor', '4f431d49-e07d-440c-9d73-a9ecac23c33a');

insert into address (id, address, city_id) values ('864290ce-ca42-4d86-8e81-231e67e9e63b', 'Street 123', 'eec398cc-c7a2-4159-9004-9f94d2ee1d8b');
insert into address (id, address, city_id) values ('46b0dafa-1bc2-4200-93e3-2712699079f8', 'Street 123', 'eec398cc-c7a2-4159-9004-9f94d2ee1d8b');

insert into users (id) values ('c0ab16f0-4aca-4e49-9188-96b4ac5b05bc');
insert into users (id) values ('ff1e1b8e-d87c-4f60-812a-f123b875d668');
insert into company (id, company_name, tax_identification_number, company_registration_number, phone_number, address_id) values ('d876450f-40b6-425a-b33e-d8e0ed597489', 'Mordor&Co Inc', '127549984', '99485732', '+381 699749238492', '864290ce-ca42-4d86-8e81-231e67e9e63b');
insert into company (id, company_name, tax_identification_number, company_registration_number, phone_number, address_id) values ('98efa33d-e953-4fb3-9418-996c9e12769d', 'Antwerp Trading ', '948483748', '19435732', '+381 645345345454', '46b0dafa-1bc2-4200-93e3-2712699079f8');
insert into employment (company_id, employee_id, role) values ('d876450f-40b6-425a-b33e-d8e0ed597489', 'c0ab16f0-4aca-4e49-9188-96b4ac5b05bc', 'COMPANY_OWNER');
insert into employment (company_id, employee_id, role) values ('98efa33d-e953-4fb3-9418-996c9e12769d', 'ff1e1b8e-d87c-4f60-812a-f123b875d668', 'COMPANY_OWNER');

insert into collaboration (company_id, client_id) values ('d876450f-40b6-425a-b33e-d8e0ed597489', '98efa33d-e953-4fb3-9418-996c9e12769d');

insert into local_currency_bank_account(id, account_number, e_banking_format, company_id) values ('f785f055-19ce-4731-8431-4dea44846b46', '125-0381695557391-74', 'HALCOM', 'd876450f-40b6-425a-b33e-d8e0ed597489');
insert into local_currency_bank_account(id, account_number, e_banking_format, company_id) values ('6d7e356e-e570-4c29-8bb0-e32efecc16c0', '125-0381685557391-55', 'HALCOM', '98efa33d-e953-4fb3-9418-996c9e12769d');