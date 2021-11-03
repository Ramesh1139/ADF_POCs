CREATE OR REPLACE PACKAGE BODY DP_MIG_XSIRPM2 AS

PROCEDURE PL_INIT_VARIABLE1(I_w_div IN VARCHAR2,I_sims_invno_from IN VARCHAR2,O_cuscod OUT VARCHAR2,O_dptcod OUT VARCHAR2)
IS
BEGIN
 SELECT sims_cuscod , sims_dptcod INTO O_cuscod , O_dptcod FROM v_tsi_mst WHERE sims_divcod = I_w_div AND sims_invno = I_sims_invno_from;
END PL_INIT_VARIABLE1;
PROCEDURE PL_INIT_VARIABLE2(I_inv_repcode IN VARCHAR2,O_inv_backgrd OUT VARCHAR2)
IS
BEGIN
 SELECT DISTINCT mpre_nam INTO O_inv_backgrd FROM tmp_rptmst , tmp_rptdiv , vap_div WHERE upper ( mpre_rptcod ) = upper ( I_inv_repcode ) AND tmp_rptmst.mpre_rptcod = tmp_rptdiv.mprd_rptcod AND tmp_rptmst.mpre_typ = 'IV' AND ( ( tmp_rptdiv.mprd_divcod = '*' ) OR ( tmp_rptdiv.mprd_divcod = vap_div.opdv_divcod ) );
END PL_INIT_VARIABLE2;

PROCEDURE PL_PRINT_SAMINV1(I_inv_backgrd IN VARCHAR2,I_g_divcod IN VARCHAR2,O_inv_repcode OUT VARCHAR2)
IS
BEGIN
 SELECT DISTINCT mpre_rptcod INTO O_inv_repcode FROM tmp_rptmst , tmp_rptdiv WHERE mpre_rptcod = mprd_rptcod AND mpre_typ = 'IV' AND ( mprd_divcod = '*' OR mprd_divcod = I_g_divcod OR I_g_divcod = 'EDP' ) AND upper ( mpre_nam ) = upper ( I_inv_backgrd );
END PL_PRINT_SAMINV1;
PROCEDURE PL_PRINT_SAMINV2(I_w_div IN VARCHAR2,I_sims_cuscod IN VARCHAR2/*CheckType*/,O_vlinfeed OUT VARCHAR2)
IS
BEGIN
 SELECT nvl ( opst_val , '7' ) INTO O_vlinfeed FROM top_setting WHERE opst_setcod = '220' AND opst_divcod = I_w_div AND opst_cuscod = I_sims_cuscod;
END PL_PRINT_SAMINV2;
PROCEDURE PL_PRINT_SAMINV3(I_w_div IN VARCHAR2,O_vlinfeed OUT VARCHAR2)
IS
BEGIN
 SELECT nvl ( opst_val , '7' ) INTO O_vlinfeed FROM top_setting WHERE opst_setcod = '220' AND opst_divcod = I_w_div AND opst_cuscod = '*';
END PL_PRINT_SAMINV3;

PROCEDURE PL_PRINT1(I_SIMS_INVNO_FROM IN VARCHAR2,I_SIMS_INVNO_TO IN VARCHAR2,I_g_divcod IN VARCHAR2,O_vcount OUT NUMBER)
IS
BEGIN
 SELECT count ( sims_divcod ) INTO O_vcount FROM v_tsi_mst WHERE sims_divcod = I_g_divcod AND ( sims_invno >= I_SIMS_INVNO_FROM OR I_SIMS_INVNO_FROM IS NULL ) AND ( sims_invno <= I_SIMS_INVNO_TO OR I_SIMS_INVNO_TO IS NULL ) AND ( sims_clsdat IS NULL OR sims_clsdat > sysdate );
END PL_PRINT1;

PROCEDURE PL_LOCK_SAMPLE_INVOICE1(I_p_sims_invno_from IN VARCHAR2,I_g_divcod IN VARCHAR2,O_saminvrun OUT NUMBER)
IS
BEGIN
 SELECT sims_saminvrun INTO O_saminvrun FROM v_tsi_mst WHERE sims_divcod = I_g_divcod AND sims_invno = I_p_sims_invno_from;
END PL_LOCK_SAMPLE_INVOICE1;
PROCEDURE PL_LOCK_SAMPLE_INVOICE2(I_saminvrun IN NUMBER,I_g_divcod IN VARCHAR2,O_saminvrun OUT NUMBER)
IS
BEGIN
 SELECT sims_saminvrun INTO O_saminvrun FROM tsi_mst WHERE sims_divcod = I_g_divcod AND sims_saminvrun = I_saminvrun FOR UPDATE OF sims_saminvrun NOWAIT;
END PL_LOCK_SAMPLE_INVOICE2;
PROCEDURE PL_LOCK_SAMPLE_INVOICE3(I_g_divcod IN VARCHAR2,I_g_lock_runnum IN VARCHAR2,O_temp_num OUT VARCHAR2)
IS
BEGIN
 SELECT sims_invno INTO O_temp_num FROM v_tsi_mst WHERE sims_divcod = I_g_divcod AND sims_saminvrun = I_g_lock_runnum;
END PL_LOCK_SAMPLE_INVOICE3;

PROCEDURE PL_NFI1(I_g_userid IN VARCHAR2,I_g_module IN VARCHAR2,O_w_prt OUT VARCHAR2)
IS
BEGIN
 SELECT nvl ( apga_prt , 'N' ) INTO O_w_prt FROM tap_usrmst , tap_autgrpatt WHERE apus_userid = I_g_userid AND apus_autgrp = apga_autgrp AND apga_module = I_g_module;
END PL_NFI1;

END DP_MIG_XSIRPM2;

