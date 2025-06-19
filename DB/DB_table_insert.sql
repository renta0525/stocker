INSERT INTO users (name, mail, password, admin_flg)
VALUES ('管理者', 'admin@example.com', '$2a$10$s05xWiRbSU9GT6F9PHctAO4GuD3TBsW6BCKZQ4PUeIZcW9tAvOCeG', true);

INSERT INTO users (name, mail, password, admin_flg)
VALUES ('一般', 'user@example.com', '$2a$10$pJLIfMLR3t.6dpzihnEZ8eMwMeuU1VciN71mSpB8lyQneCB2UrLY6', false);

INSERT INTO users (name, mail, password, admin_flg)
VALUES ('一般2', 'user2@example.com', '$2a$10$1VmAfFcreBa1YSC0N1b4H.3RybIMz4Kgr287J8vBTewLBKmqoA9om', false);

-- 店舗1: 東京本店
INSERT INTO shops (name, user_id, image_path, del_flg)
VALUES ('東京本店', 2, 'images/tokyo.png', FALSE);

-- 店舗2: 大阪支店
INSERT INTO shops (name, user_id, image_path, del_flg)
VALUES ('大阪支店', 2, 'images/osaka.png', FALSE);

-- 店舗3: 名古屋支店
INSERT INTO shops (name, user_id, image_path, del_flg)
VALUES ('名古屋支店', 2, 'images/nagoya.png', FALSE);

-- 店舗4: 北海道本店
INSERT INTO shops (name, user_id, image_path, del_flg)
VALUES ('北海道本店', 3, 'images/hokkaido.png', FALSE);

-- 店舗5: 沖縄支店
INSERT INTO shops (name, user_id, image_path, del_flg)
VALUES ('沖縄支店', 3, 'images/okinawa.png', FALSE);

-- 店舗6: 京都支店
INSERT INTO shops (name, user_id, image_path, del_flg)
VALUES ('京都支店', 3, 'images/kyoto.png', FALSE);

-- カテゴリ登録
INSERT INTO categories (name) VALUES ('食品');
INSERT INTO categories (name) VALUES ('文具');
INSERT INTO categories (name) VALUES ('電化製品');

-- 東京本店の在庫（文具カテゴリ）
INSERT INTO stocks (name, price, quantity, shop_id, category_id, memo, boundary_value, image_path, del_flg)
VALUES ('ボールペン', '120', 8, 1, 2, '黒インク', 5, 'images/pen.png', FALSE);

-- 大阪支店の在庫（食品カテゴリ）
INSERT INTO stocks (name, price, quantity, shop_id, category_id, memo, boundary_value, image_path, del_flg)
VALUES ('カップラーメン', '150', 3, 2, 1, 'しょうゆ味', 5, 'images/ramen.png', FALSE);

-- 名古屋支店の在庫（電化製品カテゴリ）
INSERT INTO stocks (name, price, quantity, shop_id, category_id, memo, boundary_value, image_path, del_flg)
VALUES ('モバイルバッテリー', '1980', 12, 3, 3, '5000mAh', 10, 'images/battery.png', FALSE);

-- 北海道本店の在庫（文具カテゴリ）
INSERT INTO stocks (name, price, quantity, shop_id, category_id, memo, boundary_value, image_path, del_flg)
VALUES ('鉛筆', '80', 8, 4, 2, '赤', 5, 'images/pencil.png', FALSE);

-- 沖縄支店の在庫（食品カテゴリ）
INSERT INTO stocks (name, price, quantity, shop_id, category_id, memo, boundary_value, image_path, del_flg)
VALUES ('パイナップル', '250', 3, 5, 1, '甘い', 5, 'images/pine.png', FALSE);

-- 京都支店の在庫（電化製品カテゴリ）
INSERT INTO stocks (name, price, quantity, shop_id, category_id, memo, boundary_value, image_path, del_flg)
VALUES ('switch2', '39800', 12, 6, 3, '転売用', 10, 'images/switch2.png', FALSE);


-- カップラーメンの在庫が閾値を下回った時の通知履歴（stock_id = 2 と仮定）
INSERT INTO message_history (stock_id, message)
VALUES (2, '在庫警告：カップラーメンの在庫数が 3 になりました（閾値: 5）');

-- モバイルバッテリーの通知履歴（stock_id = 3 と仮定）
INSERT INTO message_history (stock_id, message)
VALUES (3, '在庫警告：モバイルバッテリーの在庫数が 12 になりました（閾値: 10）');

-- パイナップルの在庫が閾値を下回った時の通知履歴（stock_id = 5 と仮定）
INSERT INTO message_history (stock_id, message)
VALUES (5, '在庫警告：パイナップルの在庫数が 3 になりました（閾値: 5）');

-- モバイルバッテリーの通知履歴（stock_id = 6 と仮定）
INSERT INTO message_history (stock_id, message)
VALUES (6, '在庫警告：switch2の在庫数が 12 になりました（閾値: 10）');
