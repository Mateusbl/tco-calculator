CREATE TABLE vehicle_price_cache (
    id BIGSERIAL PRIMARY KEY,
    brand_code INTEGER NOT NULL,
    model_code INTEGER NOT NULL,
    year_code VARCHAR(20) NOT NULL,
    price NUMERIC(15,2) NOT NULL,
    fetched_at DATE NOT NULL
);