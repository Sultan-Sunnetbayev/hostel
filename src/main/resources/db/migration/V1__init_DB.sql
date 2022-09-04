CREATE TABLE "roles"(
    "id" SERIAL NOT NULL ,
    "name" CHARACTER VARYING(50) NOT NULL UNIQUE ,
    "created" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    "updated" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,

    CONSTRAINT "roles_id_pk"
        PRIMARY KEY("id")
);

CREATE TABLE "users"(
    "id" SERIAL NOT NULL ,
    "name"  CHARACTER VARYING(35) NOT NULL ,
    "surname" CHARACTER VARYING(40) NOT NULL ,
    "image_path" CHARACTER VARYING(255) ,
    "email" CHARACTER VARYING(75) NOT NULL UNIQUE ,
    "password" CHARACTER VARYING(255) NOT NULL ,
    "role_id" INT ,
    "created" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    "updated" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,

    CONSTRAINT "users_id_pk"
        PRIMARY KEY ("id") ,
    CONSTRAINT "users_role_id_fk"
        FOREIGN KEY ("role_id")
            REFERENCES "roles"("id")
                ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE "students"(
    "id" SERIAL NOT NULL ,
    "name"  CHARACTER VARYING(35) NOT NULL ,
    "surname" CHARACTER VARYING(40) NOT NULL ,
    "patronymic_name" CHARACTER VARYING (45) ,
    "image_path" CHARACTER VARYING(255) ,
    "gender" CHARACTER VARYING (5) ,
    "training_year" INT NOT NULL ,
    "hostel" BOOLEAN DEFAULT FALSE ,
    "created" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    "updated" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,

    CONSTRAINT "students_id_pk"
        PRIMARY KEY ("id")
);

CREATE TABLE "participation_notebook"(
    "id" SERIAL NOT NULL ,
    "name" CHARACTER VARYING (500) NOT NULL ,
    "created" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    "updated" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,

    CONSTRAINT "participation_notebook_id_pk"
        PRIMARY KEY ("id")
);

CREATE TABLE "participation_notebook_students"(

    "student_id" INT NOT NULL,
    "participation_notebook_id" INT NOT NULL ,
    "status" BOOLEAN DEFAULT TRUE ,
    "created" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    "updated" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,

    CONSTRAINT "participation_notebook_students_student_id_fk"
        FOREIGN KEY ("student_id")
            REFERENCES "students"("id")
                ON UPDATE CASCADE ON DELETE CASCADE ,
    CONSTRAINT "participation_notebook_students_participation_notebook_id_fk"
        FOREIGN KEY ("participation_notebook_id")
            REFERENCES "participation_notebook"("id")
                ON UPDATE CASCADE ON DELETE CASCADE
 );