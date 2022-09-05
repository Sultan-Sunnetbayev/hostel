INSERT INTO "users"("name","surname","email","password","role_id") VALUES('admin',
                                                                          'adminow',
                                                                          'admin@admin.com',
                                                                          '$2a$12$yRyx1QoxUftNwcSCaeADq.ohHC7WM4emaDsZshNHYWQiVc3bQiX8m',
                                                                          (SELECT id FROM "roles" WHERE name='ROLE_ADMIN')
                                                                         );