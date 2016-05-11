CREATE TABLE positron.t_object (id INTEGER NOT NULL, DTYPE VARCHAR(31), name VARCHAR(255), PRIMARY KEY (id))
CREATE TABLE positron.t_building (id INTEGER NOT NULL, energy INTEGER, energy_max INTEGER, lat FLOAT, level INTEGER, lifetime INTEGER, long FLOAT, radius INTEGER, MDAMAGE INTEGER, PRIMARY KEY (id))
CREATE TABLE positron.t_portal (portal_id INTEGER NOT NULL, latitude FLOAT, longitude FLOAT, radius INTEGER, team_fk INTEGER, PRIMARY KEY (portal_id))
CREATE TABLE positron.t_field (field_id INTEGER NOT NULL, territory_fk INTEGER, PRIMARY KEY (field_id))
CREATE TABLE positron.t_player (id INTEGER NOT NULL, bag_size INTEGER, email VARCHAR(255), energy INTEGER, energy_max INTEGER, mLatitude FLOAT, longitude FLOAT, nickname VARCHAR(255), xp INTEGER, team INTEGER, PRIMARY KEY (id))
CREATE TABLE positron.t_key (id INTEGER NOT NULL, portal_fk INTEGER, PRIMARY KEY (id))
CREATE TABLE positron.t_consumable (id INTEGER NOT NULL, rarity INTEGER, PRIMARY KEY (id))
CREATE TABLE positron.t_resonator (id INTEGER NOT NULL, owner_fk INTEGER, portal_fk INTEGER, PRIMARY KEY (id))
CREATE TABLE positron.t_link (link_id INTEGER NOT NULL, field_fk INTEGER, PRIMARY KEY (link_id))
CREATE TABLE t_team (id INTEGER NOT NULL, color VARCHAR(255), PRIMARY KEY (id))
CREATE TABLE positron.t_territory (territory_id INTEGER NOT NULL, PRIMARY KEY (territory_id))
CREATE TABLE positron.t_skill (id INTEGER NOT NULL, cost INTEGER, level INTEGER, name VARCHAR(255), PRIMARY KEY (id))
CREATE TABLE positron.portal_link (portal_fk INTEGER NOT NULL, link_fk INTEGER NOT NULL, PRIMARY KEY (portal_fk, link_fk))
CREATE TABLE positron.t_portal_t_object (CPortalEntity_portal_id INTEGER NOT NULL, mObjects_id INTEGER NOT NULL, PRIMARY KEY (CPortalEntity_portal_id, mObjects_id))
CREATE TABLE positron.t_portal_t_resonator (CPortalEntity_portal_id INTEGER NOT NULL, mResonators_id INTEGER NOT NULL, PRIMARY KEY (CPortalEntity_portal_id, mResonators_id))
CREATE TABLE positron.t_player_t_object (CPlayerEntity_id INTEGER NOT NULL, mObjects_id INTEGER NOT NULL, PRIMARY KEY (CPlayerEntity_id, mObjects_id))
CREATE TABLE positron.t_player_t_skill (CPlayerEntity_id INTEGER NOT NULL, mSkills_id INTEGER NOT NULL, PRIMARY KEY (CPlayerEntity_id, mSkills_id))
CREATE TABLE positron.t_team_t_player (CTeamEntity_id INTEGER NOT NULL, mPlayers_id INTEGER NOT NULL, PRIMARY KEY (CTeamEntity_id, mPlayers_id))
CREATE TABLE positron.t_team_t_portal (CTeamEntity_id INTEGER NOT NULL, mPortals_portal_id INTEGER NOT NULL, PRIMARY KEY (CTeamEntity_id, mPortals_portal_id))
ALTER TABLE positron.t_building ADD CONSTRAINT FK_t_building_id FOREIGN KEY (id) REFERENCES positron.t_object (id)
ALTER TABLE positron.t_portal ADD CONSTRAINT FK_t_portal_team_fk FOREIGN KEY (team_fk) REFERENCES t_team (id)
ALTER TABLE positron.t_field ADD CONSTRAINT FK_t_field_territory_fk FOREIGN KEY (territory_fk) REFERENCES positron.t_territory (territory_id)
ALTER TABLE positron.t_player ADD CONSTRAINT FK_t_player_team FOREIGN KEY (team) REFERENCES t_team (id)
ALTER TABLE positron.t_key ADD CONSTRAINT FK_t_key_portal_fk FOREIGN KEY (portal_fk) REFERENCES positron.t_portal (portal_id)
ALTER TABLE positron.t_key ADD CONSTRAINT FK_t_key_id FOREIGN KEY (id) REFERENCES positron.t_object (id)
ALTER TABLE positron.t_consumable ADD CONSTRAINT FK_t_consumable_id FOREIGN KEY (id) REFERENCES positron.t_object (id)
ALTER TABLE positron.t_resonator ADD CONSTRAINT FK_t_resonator_id FOREIGN KEY (id) REFERENCES positron.t_object (id)
ALTER TABLE positron.t_resonator ADD CONSTRAINT FK_t_resonator_owner_fk FOREIGN KEY (owner_fk) REFERENCES positron.t_player (id)
ALTER TABLE positron.t_resonator ADD CONSTRAINT FK_t_resonator_portal_fk FOREIGN KEY (portal_fk) REFERENCES positron.t_portal (portal_id)
ALTER TABLE positron.t_link ADD CONSTRAINT FK_t_link_field_fk FOREIGN KEY (field_fk) REFERENCES positron.t_field (field_id)
ALTER TABLE positron.portal_link ADD CONSTRAINT FK_portal_link_portal_fk FOREIGN KEY (portal_fk) REFERENCES positron.t_portal (portal_id)
ALTER TABLE positron.portal_link ADD CONSTRAINT FK_portal_link_link_fk FOREIGN KEY (link_fk) REFERENCES positron.t_link (link_id)
ALTER TABLE positron.t_portal_t_object ADD CONSTRAINT FK_t_portal_t_object_mObjects_id FOREIGN KEY (mObjects_id) REFERENCES positron.t_object (id)
ALTER TABLE positron.t_portal_t_object ADD CONSTRAINT FK_t_portal_t_object_CPortalEntity_portal_id FOREIGN KEY (CPortalEntity_portal_id) REFERENCES positron.t_portal (portal_id)
ALTER TABLE positron.t_portal_t_resonator ADD CONSTRAINT FK_t_portal_t_resonator_CPortalEntity_portal_id FOREIGN KEY (CPortalEntity_portal_id) REFERENCES positron.t_portal (portal_id)
ALTER TABLE positron.t_portal_t_resonator ADD CONSTRAINT FK_t_portal_t_resonator_mResonators_id FOREIGN KEY (mResonators_id) REFERENCES positron.t_object (id)
ALTER TABLE positron.t_player_t_object ADD CONSTRAINT FK_t_player_t_object_CPlayerEntity_id FOREIGN KEY (CPlayerEntity_id) REFERENCES positron.t_player (id)
ALTER TABLE positron.t_player_t_object ADD CONSTRAINT FK_t_player_t_object_mObjects_id FOREIGN KEY (mObjects_id) REFERENCES positron.t_object (id)
ALTER TABLE positron.t_player_t_skill ADD CONSTRAINT FK_t_player_t_skill_CPlayerEntity_id FOREIGN KEY (CPlayerEntity_id) REFERENCES positron.t_player (id)
ALTER TABLE positron.t_player_t_skill ADD CONSTRAINT FK_t_player_t_skill_mSkills_id FOREIGN KEY (mSkills_id) REFERENCES positron.t_skill (id)
ALTER TABLE positron.t_team_t_player ADD CONSTRAINT FK_t_team_t_player_mPlayers_id FOREIGN KEY (mPlayers_id) REFERENCES positron.t_player (id)
ALTER TABLE positron.t_team_t_player ADD CONSTRAINT FK_t_team_t_player_CTeamEntity_id FOREIGN KEY (CTeamEntity_id) REFERENCES t_team (id)
ALTER TABLE positron.t_team_t_portal ADD CONSTRAINT FK_t_team_t_portal_mPortals_portal_id FOREIGN KEY (mPortals_portal_id) REFERENCES positron.t_portal (portal_id)
ALTER TABLE positron.t_team_t_portal ADD CONSTRAINT FK_t_team_t_portal_CTeamEntity_id FOREIGN KEY (CTeamEntity_id) REFERENCES t_team (id)
