CREATE TABLE wifi (
                      manage_num          VARCHAR(100) NOT NULL,
                      region              VARCHAR(100),
                      wifi_name           VARCHAR(100),
                      road_address        VARCHAR(255),
                      detailed_address    VARCHAR(255),
                      floor               VARCHAR(255),
                      installaion_type    VARCHAR(255),
                      organizations       VARCHAR(100),
                      claasified_service  VARCHAR(100),
                      network_type        VARCHAR(100),
                      year_of_install     INT,
                      inorout             VARCHAR(100),
                      conn_environment    VARCHAR(100),
                      lat                 FLOAT,
                      lnt                 FLOAT,
                      work_time           TIMESTAMP,
                      PRIMARY KEY(manage_num)
) ENGINE=MYISAM CHARSET=utf8;

CREATE TABLE history (
                         id                  INT NOT NULL,
                         x                   FLOAT,
                         y                   FLOAT,
                         created_time        TIMESTAMP,
                         PRIMARY KEY(id)
) ENGINE=MYISAM CHARSET=utf8;