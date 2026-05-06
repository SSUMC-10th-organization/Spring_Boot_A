-- region
INSERT IGNORE INTO region (name) VALUES ('서울'), ('경기'), ('부산');

-- member
INSERT IGNORE INTO member (email, password, name, point, status, number, likes) VALUES
    ('test1@test.com', 'password1', '홍길동', 100, 'ACTIVE', '010-1234-5678', 5),
    ('test2@test.com', 'password2', '김철수', 50,  'ACTIVE', '010-9999-0000', 2);

-- store (region_id: 서울=1, 경기=2, 부산=3)
INSERT IGNORE INTO store (name, location, region_id) VALUES
    ('강남 맛집', '서울 강남구 테헤란로 1', 1),
    ('홍대 카페', '서울 마포구 홍익로 2', 1),
    ('수원 한식', '경기 수원시 팔달구 3', 2);

-- mission (store_id: 강남맛집=1, 홍대카페=2, 수원한식=3)
INSERT IGNORE INTO mission (detail, point, store_id) VALUES
    ('음료 2잔 이상 주문하기', 50, 1),
    ('혼자 방문해서 먹기', 30, 2),
    ('점심 시간에 방문하기', 20, 3);

-- member_mission (member_id, mission_id, store_id, region_id)
INSERT IGNORE INTO member_mission (mission_count, status, member_id, mission_id, store_id, region_id) VALUES
    (1, 'COMPLETE', 1, 1, 1, 1),
    (0, 'PROGRESS', 1, 2, 2, 1),
    (0, 'PROGRESS', 2, 3, 3, 2);

-- review (member_id, store_id, region_id)
INSERT IGNORE INTO review (score, detail, member_id, store_id, region_id) VALUES
    (5, '음식이 정말 맛있어요!', 1, 1, 1),
    (4, '분위기가 좋아요.',     2, 2, 1);
