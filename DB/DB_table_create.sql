-- テーブル一覧

-- ユーザー
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    mail VARCHAR NOT NULL UNIQUE,
    password VARCHAR NOT NULL,
    admin_flg BOOLEAN NOT NULL DEFAULT FALSE,
    del_flg BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)

-- 店舗情報
CREATE TABLE shops (
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    user_id INTEGER NOT NULL,
    image_path VARCHAR,
    del_flg BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (user_id) REFERENCES users(id)
)

-- カテゴリー
CREATE TABLE categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL
);

-- 在庫情報
CREATE TABLE stocks (
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    price VARCHAR NOT NULL,
    quantity INTEGER NOT NULL,
    shop_id INTEGER NOT NULL,
    category_id INTEGER NOT NULL,
    memo VARCHAR,
    boundary_value INTEGER DEFAULT 5,
    image_path VARCHAR,
    del_flg BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (shop_id) REFERENCES shops(id),
    FOREIGN KEY (category_id) REFERENCES categories(id)
)

-- 通知履歴ログ
CREATE TABLE message_history (
    id SERIAL PRIMARY KEY,
    stock_id INTEGER NOT NULL,
    message VARCHAR NOT NULL,
    message_history_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (stock_id) REFERENCES stocks(id)
)

-- 購入テーブル
CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    stock_id INTEGER NOT NULL,
    quantity INTEGER NOT NULL,
    user_name VARCHAR NOT NULL,
    total_price INTEGER NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (stock_id) REFERENCES stocks(id)
);
