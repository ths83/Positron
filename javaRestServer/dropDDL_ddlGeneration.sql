ALTER TABLE positron.t_building DROP CONSTRAINT FK_t_building_id
ALTER TABLE positron.t_portal DROP CONSTRAINT FK_t_portal_team_fk
ALTER TABLE positron.t_field DROP CONSTRAINT FK_t_field_territory_fk
ALTER TABLE positron.t_player DROP CONSTRAINT FK_t_player_team
ALTER TABLE positron.t_key DROP CONSTRAINT FK_t_key_portal_fk
ALTER TABLE positron.t_key DROP CONSTRAINT FK_t_key_id
ALTER TABLE positron.t_consumable DROP CONSTRAINT FK_t_consumable_id
ALTER TABLE positron.t_resonator DROP CONSTRAINT FK_t_resonator_id
ALTER TABLE positron.t_resonator DROP CONSTRAINT FK_t_resonator_owner_fk
ALTER TABLE positron.t_resonator DROP CONSTRAINT FK_t_resonator_portal_fk
ALTER TABLE positron.t_link DROP CONSTRAINT FK_t_link_field_fk
ALTER TABLE positron.portal_link DROP CONSTRAINT FK_portal_link_portal_fk
ALTER TABLE positron.portal_link DROP CONSTRAINT FK_portal_link_link_fk
ALTER TABLE positron.t_portal_t_object DROP CONSTRAINT FK_t_portal_t_object_mObjects_id
ALTER TABLE positron.t_portal_t_object DROP CONSTRAINT FK_t_portal_t_object_CPortalEntity_portal_id
ALTER TABLE positron.t_portal_t_resonator DROP CONSTRAINT FK_t_portal_t_resonator_CPortalEntity_portal_id
ALTER TABLE positron.t_portal_t_resonator DROP CONSTRAINT FK_t_portal_t_resonator_mResonators_id
ALTER TABLE positron.t_player_t_object DROP CONSTRAINT FK_t_player_t_object_CPlayerEntity_id
ALTER TABLE positron.t_player_t_object DROP CONSTRAINT FK_t_player_t_object_mObjects_id
ALTER TABLE positron.t_player_t_skill DROP CONSTRAINT FK_t_player_t_skill_CPlayerEntity_id
ALTER TABLE positron.t_player_t_skill DROP CONSTRAINT FK_t_player_t_skill_mSkills_id
ALTER TABLE positron.t_team_t_player DROP CONSTRAINT FK_t_team_t_player_mPlayers_id
ALTER TABLE positron.t_team_t_player DROP CONSTRAINT FK_t_team_t_player_CTeamEntity_id
ALTER TABLE positron.t_team_t_portal DROP CONSTRAINT FK_t_team_t_portal_mPortals_portal_id
ALTER TABLE positron.t_team_t_portal DROP CONSTRAINT FK_t_team_t_portal_CTeamEntity_id
DROP TABLE positron.t_object CASCADE
DROP TABLE positron.t_building CASCADE
DROP TABLE positron.t_portal CASCADE
DROP TABLE positron.t_field CASCADE
DROP TABLE positron.t_player CASCADE
DROP TABLE positron.t_key CASCADE
DROP TABLE positron.t_consumable CASCADE
DROP TABLE positron.t_resonator CASCADE
DROP TABLE positron.t_link CASCADE
DROP TABLE positron.t_team CASCADE
DROP TABLE positron.t_territory CASCADE
DROP TABLE positron.t_skill CASCADE
DROP TABLE positron.portal_link CASCADE
DROP TABLE positron.t_portal_t_object CASCADE
DROP TABLE positron.t_portal_t_resonator CASCADE
DROP TABLE positron.t_player_t_object CASCADE
DROP TABLE positron.t_player_t_skill CASCADE
DROP TABLE positron.t_team_t_player CASCADE
DROP TABLE positron.t_team_t_portal CASCADE
