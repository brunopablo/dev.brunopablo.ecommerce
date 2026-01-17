-- Sintaxe da query ajustado para H2, caso utilizar PostgreSQL descomentar ON CONFLICT (isto impede valores duplicados)

-- tb_products-
INSERT INTO tb_products (name_product, price)
    VALUES
        ('Computer', 4500.50),
        ('Smartphone', 2000.00),
        ('Mouse', 200.00);
    -- ON CONFLICT (id_product) DO NOTHING;

-- tb_tags
INSERT INTO tb_tags (name)
    VALUES
        ('Eletronics'),
        ('Home'),
        ('Apple');
    -- ON CONFLICT (id_tag) DO NOTHING;

-- tb_products_tags
INSERT INTO tb_products_tags (id_product, id_tag)
    VALUES
        (1, 1),
        (2, 3),
        (2, 1),
        (3, 1);
    -- ON CONFLICT (id_product, id_tag) DO NOTHING;