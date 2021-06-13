create table if not exists accounts
(
    id bigint  generated by default as identity
    constraint accounts_pkey
    primary key,
    document_number varchar(255) not null
    );

create table if not exists operation_type
(
    id bigint generated by default as identity
    constraint operation_type_pkey
    primary key,
    description varchar(255) not null,
    sign_operation varchar(255) not null
    );

create table if not exists transactions
(
    id bigint generated by default as identity
    constraint transaction_pkey
    primary key,
    amount numeric(19,2) not null,
    account_id bigint
    constraint fkaqehs9hoj644s1sab3ge0wrs3
    references accounts,
    operation_type_id bigint not null
    constraint fkj2jvxwruf2hchy3jj25ne5m07
    references operation_type
    );

insert into operation_type (description, sign_operation)
values ('COMPRA A VISTA', 'NEGATIVE');

insert into operation_type (description, sign_operation)
values ('COMPRA PARCELADA', 'NEGATIVE');

insert into operation_type (description, sign_operation)
values ('SAQUE', 'NEGATIVE');

insert into operation_type (description, sign_operation)
values ('PAGAMENTO', 'POSITIVE');