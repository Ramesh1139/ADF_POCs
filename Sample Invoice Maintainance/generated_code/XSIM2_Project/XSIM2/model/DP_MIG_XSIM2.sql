CREATE OR REPLACE PACKAGE BODY DP_MIG_XSIM2 AS

PROCEDURE PL_VALIDATE_CUSCOD1(I_SIMS_CUSCOD IN VARCHAR2,I_G_DIVCOD IN VARCHAR2,O_I OUT NUMBER)
IS
BEGIN
 SELECT COUNT ( * ) INTO O_I FROM TCP_MST , TCP_ALL WHERE CPMS_CUSCOD = CPAL_CUSCOD AND CPMS_DIVCOD = I_G_DIVCOD AND UPPER ( CPMS_STSCOD ) = 'AT' AND UPPER ( CPAL_CUSCOD ) = UPPER ( I_SIMS_CUSCOD );
END PL_VALIDATE_CUSCOD1;
PROCEDURE PL_VALIDATE_CUSCOD2(I_SIMS_CUSCOD IN VARCHAR2,O_CPAL_NAM OUT VARCHAR2)
IS
BEGIN
 SELECT CPAL_NAM INTO O_CPAL_NAM FROM TCP_ALL WHERE UPPER ( CPAL_CUSCOD ) = UPPER ( I_SIMS_CUSCOD );
END PL_VALIDATE_CUSCOD2;

PROCEDURE PL_VALIDATE_PORT_L1(I_SIMS_PORT_L IN VARCHAR2,O_I OUT NUMBER)
IS
BEGIN
 SELECT COUNT ( * ) INTO O_I FROM TMP_CTYPORMST WHERE UPPER ( MPCP_PORT ) = UPPER ( I_SIMS_PORT_L );
END PL_VALIDATE_PORT_L1;
PROCEDURE PL_VALIDATE_PORT_L2(I_SIMS_PORT_L IN VARCHAR2,O_MPCP_NAM_L OUT VARCHAR2)
IS
BEGIN
 SELECT MPCP_NAM INTO O_MPCP_NAM_L FROM TMP_CTYPORMST WHERE UPPER ( MPCP_PORT ) = UPPER ( I_SIMS_PORT_L );
END PL_VALIDATE_PORT_L2;

PROCEDURE PL_VALIDATE_SHPMOD1(I_SIMS_SHPMODCOD IN VARCHAR2,O_I OUT NUMBER)
IS
BEGIN
 SELECT COUNT ( * ) INTO O_I FROM tmp_shpmodmst WHERE UPPER ( MPSM_SHPMODCOD ) = UPPER ( I_SIMS_SHPMODCOD );
END PL_VALIDATE_SHPMOD1;
PROCEDURE PL_VALIDATE_SHPMOD2(I_SIMS_SHPMODCOD IN VARCHAR2,O_MPSM_DES OUT VARCHAR2)
IS
BEGIN
 SELECT MPSM_DES INTO O_MPSM_DES FROM TMP_SHPMODMST WHERE UPPER ( MPSM_SHPMODCOD ) = UPPER ( I_SIMS_SHPMODCOD );
END PL_VALIDATE_SHPMOD2;

PROCEDURE PL_VALIDATE_ADRCOD1(I_SIMS_ADRCOD IN VARCHAR2,I_SIMS_CUSCOD IN VARCHAR2,O_I OUT NUMBER)
IS
BEGIN
 SELECT COUNT ( * ) INTO O_I FROM TCP_ADR WHERE UPPER ( CPAD_ADRCOD ) = UPPER ( I_SIMS_ADRCOD ) AND UPPER ( CPAD_CUSCOD ) = UPPER ( I_SIMS_CUSCOD );
END PL_VALIDATE_ADRCOD1;
PROCEDURE PL_VALIDATE_ADRCOD2(I_SIMS_CUSCOD IN VARCHAR2,I_SIMS_ADRCOD IN VARCHAR2,O_SIMS_ADR1 OUT VARCHAR2,O_SIMS_ADR2 OUT VARCHAR2,O_SIMS_ADR3 OUT VARCHAR2,O_SIMS_ADR4 OUT VARCHAR2)
IS
BEGIN
 SELECT CPAD_ADR1 , CPAD_ADR2 , CPAD_ADR3 , CPAD_ADR4 INTO O_SIMS_ADR1 , O_SIMS_ADR2 , O_SIMS_ADR3 , O_SIMS_ADR4 FROM TCP_ADR WHERE UPPER ( CPAD_CUSCOD ) = UPPER ( I_SIMS_CUSCOD ) AND UPPER ( CPAD_ADRCOD ) = UPPER ( I_SIMS_ADRCOD );
END PL_VALIDATE_ADRCOD2;

PROCEDURE PL_VALIDATE_PORT_D1(I_SIMS_PORT_D IN VARCHAR2,O_I OUT NUMBER)
IS
BEGIN
 SELECT COUNT ( * ) INTO O_I FROM TMP_CTYPORMST WHERE UPPER ( MPCP_PORT ) = UPPER ( I_SIMS_PORT_D );
END PL_VALIDATE_PORT_D1;
PROCEDURE PL_VALIDATE_PORT_D2(I_SIMS_PORT_D IN VARCHAR2,O_MPCP_NAM_D OUT VARCHAR2)
IS
BEGIN
 SELECT MPCP_NAM INTO O_MPCP_NAM_D FROM TMP_CTYPORMST WHERE UPPER ( MPCP_PORT ) = UPPER ( I_SIMS_PORT_D );
END PL_VALIDATE_PORT_D2;

PROCEDURE PL_VALIDATE_DPTCOD1(I_SIMS_DPTCOD IN VARCHAR2,I_W_DIV IN VARCHAR2,O_I OUT NUMBER)
IS
BEGIN
 SELECT COUNT ( * ) INTO O_I FROM TOP_DPTMST WHERE UPPER ( OPDP_DPTCOD ) = UPPER ( I_SIMS_DPTCOD ) AND UPPER ( OPDP_DIVCOD ) = I_W_DIV;
END PL_VALIDATE_DPTCOD1;
PROCEDURE PL_VALIDATE_DPTCOD2(I_SIMS_DPTCOD IN VARCHAR2,I_W_DIV IN VARCHAR2,O_OPDP_NAM OUT VARCHAR2)
IS
BEGIN
 SELECT OPDP_NAM INTO O_OPDP_NAM FROM TOP_DPTMST WHERE UPPER ( OPDP_DPTCOD ) = UPPER ( I_SIMS_DPTCOD ) AND UPPER ( OPDP_DIVCOD ) = I_W_DIV;
END PL_VALIDATE_DPTCOD2;

PROCEDURE PL_VALIDATE_DIVCOD1(I_SIMS_DIVCOD IN VARCHAR2,O_I OUT NUMBER)
IS
BEGIN
 SELECT COUNT ( * ) INTO O_I FROM TOP_DIVMST WHERE UPPER ( OPDV_DIVCOD ) = UPPER ( I_SIMS_DIVCOD );
END PL_VALIDATE_DIVCOD1;

PROCEDURE PL_VALIDATE_VIA1(I_SIMS_VIA IN VARCHAR2,O_I OUT NUMBER)
IS
BEGIN
 SELECT COUNT ( * ) INTO O_I FROM TMP_CTYPORMST WHERE UPPER ( MPCP_PORT ) = UPPER ( I_SIMS_VIA );
END PL_VALIDATE_VIA1;
PROCEDURE PL_VALIDATE_VIA2(I_SIMS_VIA IN VARCHAR2,O_MPCP_NAM_VIA OUT VARCHAR2)
IS
BEGIN
 SELECT MPCP_NAM INTO O_MPCP_NAM_VIA FROM TMP_CTYPORMST WHERE UPPER ( MPCP_PORT ) = UPPER ( I_SIMS_VIA );
END PL_VALIDATE_VIA2;

PROCEDURE PL_VALIDATE_CHRTYP1(I_SIMS_CHRTYP IN VARCHAR2,O_I OUT NUMBER)
IS
BEGIN
 SELECT COUNT ( * ) INTO O_I FROM TSI_CHRTYP WHERE UPPER ( SICT_TYP ) = UPPER ( I_SIMS_CHRTYP );
END PL_VALIDATE_CHRTYP1;
PROCEDURE PL_VALIDATE_CHRTYP2(I_SIMS_CHRTYP IN VARCHAR2,O_SICT_DES OUT VARCHAR2)
IS
BEGIN
 SELECT SICT_DES INTO O_SICT_DES FROM TSI_CHRTYP WHERE UPPER ( SICT_TYP ) = UPPER ( I_SIMS_CHRTYP );
END PL_VALIDATE_CHRTYP2;

PROCEDURE PL_VALIDATE_CARCOD1(I_SIMS_CARCOD IN VARCHAR2,O_I OUT NUMBER)
IS
BEGIN
 SELECT COUNT ( * ) INTO O_I FROM TMP_CARMST WHERE UPPER ( MPCA_CARCOD ) = UPPER ( I_SIMS_CARCOD );
END PL_VALIDATE_CARCOD1;
PROCEDURE PL_VALIDATE_CARCOD2(I_SIMS_CARCOD IN VARCHAR2,O_MPCA_CARNAM OUT VARCHAR2)
IS
BEGIN
 SELECT MPCA_CARNAM INTO O_MPCA_CARNAM FROM TMP_CARMST WHERE UPPER ( MPCA_CARCOD ) = UPPER ( I_SIMS_CARCOD );
END PL_VALIDATE_CARCOD2;

PROCEDURE PL_VALIDATE_CONPERCOD1(I_sims_cuscod IN VARCHAR2,I_G_DIVCOD IN VARCHAR2,O_I OUT NUMBER)
IS
BEGIN
 SELECT COUNT ( * ) INTO O_I FROM TCP_CONPER WHERE CPCP_DIVCOD = I_G_DIVCOD AND cpcp_cuscod = I_sims_cuscod AND ( cpcp_clsdat IS NULL OR cpcp_clsdat > sysdate );
END PL_VALIDATE_CONPERCOD1;
PROCEDURE PL_VALIDATE_CONPERCOD2(I_sims_cuscod IN VARCHAR2,I_SIMS_CONPERCOD IN VARCHAR2,I_G_DIVCOD IN VARCHAR2,O_CPCP_CONPERNAM OUT VARCHAR2)
IS
BEGIN
 SELECT CPCP_SURNAM || ' ' || CPCP_FSTNAM INTO O_CPCP_CONPERNAM FROM TCP_CONPER WHERE CPCP_DIVCOD = I_G_DIVCOD AND cpcp_cuscod = I_sims_cuscod AND CPCP_CONPERCOD = I_SIMS_CONPERCOD;
END PL_VALIDATE_CONPERCOD2;

PROCEDURE PL_FL_CREATE_TEXT1(I_sims_saminvrun IN NUMBER,I_g_divcod IN VARCHAR2,O_lock_runnum OUT NUMBER)
IS
BEGIN
 SELECT sims_saminvrun INTO O_lock_runnum FROM tsi_mst WHERE sims_divcod = I_g_divcod AND sims_saminvrun = I_sims_saminvrun FOR UPDATE OF sims_saminvrun NOWAIT;
END PL_FL_CREATE_TEXT1;
PROCEDURE PL_FL_CREATE_TEXT2(I_sims_saminvrun IN NUMBER,I_g_divcod IN VARCHAR2)
IS
BEGIN
 UPDATE tsi_mst SET sims_snddat = sysdate , sims_samsnd = 'N' WHERE sims_divcod = I_g_divcod AND SIMS_SAMINVRUN = I_sims_saminvrun;
END PL_FL_CREATE_TEXT2;

PROCEDURE PL_BLOCK_UPDATE_OK1(I_sims_saminvrun IN NUMBER,I_g_divcod IN VARCHAR2,O_lock_runnum OUT NUMBER)
IS
BEGIN
 SELECT sims_saminvrun INTO O_lock_runnum FROM tsi_mst WHERE sims_divcod = I_g_divcod AND sims_saminvrun = I_sims_saminvrun FOR UPDATE OF sims_saminvrun NOWAIT;
END PL_BLOCK_UPDATE_OK1;

PROCEDURE PL_GET_USER_MESSAGE1(I_p_msgcod IN VARCHAR2,O_v_desc OUT VARCHAR2)
IS
BEGIN
 SELECT mpmg_desc INTO O_v_desc FROM tmp_msgmst WHERE mpmg_msgcod = I_p_msgcod AND ( mpmg_clsdat IS NULL OR mpmg_clsdat > sysdate );
END PL_GET_USER_MESSAGE1;

PROCEDURE PL_INIT_VARIABLE1(O_w_mdate OUT NUMBER)
IS
BEGIN
 SELECT to_number ( mpsy_val ) INTO O_w_mdate FROM tmp_syspara WHERE upper ( mpsy_parnam ) = 'P_LOVDTE';
END PL_INIT_VARIABLE1;

PROCEDURE PL_SHOW_INVNO1(I_SIMS_SAMINVRUN IN NUMBER,O_I OUT INTEGER)
IS
BEGIN
 SELECT COUNT ( * ) INTO O_I FROM TSI_MST WHERE SIMS_SAMINVRUN = I_SIMS_SAMINVRUN;
END PL_SHOW_INVNO1;
PROCEDURE PL_SHOW_INVNO2(I_SIMS_SAMINVRUN IN NUMBER,O_SIMS_INVNO OUT VARCHAR2)
IS
BEGIN
 SELECT SIMS_INVNO INTO O_SIMS_INVNO FROM V_TSI_MST WHERE SIMS_SAMINVRUN = I_SIMS_SAMINVRUN;
END PL_SHOW_INVNO2;

PROCEDURE PL_SET_CUST_DEFAULT1(I_sims_cuscod IN VARCHAR2,I_g_divcod IN VARCHAR2,O_cndsalcod OUT VARCHAR2,O_cndsaldes OUT VARCHAR2,O_paytrmcod OUT VARCHAR2,O_paytrmdes OUT VARCHAR2,O_sims_nam OUT VARCHAR2,O_sims_carcod OUT VARCHAR2,O_sims_adrcod OUT VARCHAR2,O_sims_adr1 OUT VARCHAR2,O_sims_adr2 OUT VARCHAR2,O_sims_adr3 OUT VARCHAR2,O_sims_adr4 OUT VARCHAR2,O_v_cpms_invdefcsg OUT VARCHAR2)
IS
BEGIN
 SELECT cpms_cndsalcod , cpms_cndsaldes , cpms_paytrmcod , cpms_paytrmdes , cpal_fulnam , cpms_carcod , cpms_adrcod , cpad_adr1 , cpad_adr2 , cpad_adr3 , cpad_adr4 , cpms_invdefcsg INTO O_cndsalcod , O_cndsaldes , O_paytrmcod , O_paytrmdes , O_sims_nam , O_sims_carcod , O_sims_adrcod , O_sims_adr1 , O_sims_adr2 , O_sims_adr3 , O_sims_adr4 , O_v_cpms_invdefcsg FROM tcp_mst , tcp_all , tcp_adr WHERE cpms_divcod = I_g_divcod AND cpms_cuscod = I_sims_cuscod AND cpms_cuscod = cpal_cuscod AND cpms_cuscod = cpad_cuscod AND cpms_adrcod = cpad_adrcod;
END PL_SET_CUST_DEFAULT1;
PROCEDURE PL_SET_CUST_DEFAULT2(I_sims_cuscod IN VARCHAR2,I_v_cpms_invdefcsg IN VARCHAR2,I_g_divcod IN VARCHAR2,O_sims_nam OUT VARCHAR2,O_sims_adr1 OUT VARCHAR2,O_sims_adr2 OUT VARCHAR2,O_sims_adr3 OUT VARCHAR2,O_sims_adr4 OUT VARCHAR2)
IS
BEGIN
 SELECT cpcs_nam , cpcs_adr1 , cpcs_adr2 , cpcs_adr3 , cpcs_adr4 INTO O_sims_nam , O_sims_adr1 , O_sims_adr2 , O_sims_adr3 , O_sims_adr4 FROM tcp_csg WHERE cpcs_divcod = I_g_divcod AND cpcs_cuscod = I_sims_cuscod AND cpcs_csgcod = I_v_cpms_invdefcsg;
END PL_SET_CUST_DEFAULT2;
PROCEDURE PL_SET_CUST_DEFAULT3(I_sims_carcod IN VARCHAR2,O_mpca_carnam OUT VARCHAR2)
IS
BEGIN
 SELECT mpca_carnam INTO O_mpca_carnam FROM tmp_carmst WHERE upper ( mpca_carcod ) = upper ( I_sims_carcod );
END PL_SET_CUST_DEFAULT3;

PROCEDURE PL_PRINT_SAMINV1(I_w_div IN VARCHAR2,I_sims_cuscod IN VARCHAR2/*CheckType*/,O_vlinfeed OUT VARCHAR2)
IS
BEGIN
 SELECT nvl ( opst_val , '7' ) INTO O_vlinfeed FROM top_setting WHERE opst_setcod = '200' AND opst_divcod = I_w_div AND opst_cuscod = I_sims_cuscod;
END PL_PRINT_SAMINV1;
PROCEDURE PL_PRINT_SAMINV2(I_w_div IN VARCHAR2,O_vlinfeed OUT VARCHAR2)
IS
BEGIN
 SELECT nvl ( opst_val , '7' ) INTO O_vlinfeed FROM top_setting WHERE opst_setcod = '200' AND opst_divcod = I_w_div AND opst_cuscod = '*';
END PL_PRINT_SAMINV2;

PROCEDURE PL_LOCK_SI1(I_divcod IN VARCHAR2,I_saminvrun IN NUMBER,O_si_run OUT NUMBER)
IS
BEGIN
 SELECT sims_saminvrun INTO O_si_run FROM tsi_mst WHERE sims_divcod = I_divcod AND sims_saminvrun = I_saminvrun FOR UPDATE OF sims_saminvrun NOWAIT;
END PL_LOCK_SI1;

PROCEDURE PL_BLK5_SAVE1(I_siit_typ IN VARCHAR2,O_i OUT NUMBER)
IS
BEGIN
 SELECT count ( 1 ) INTO O_i FROM tsi_typ WHERE sity_typ = I_siit_typ;
END PL_BLK5_SAVE1;
PROCEDURE PL_BLK5_SAVE2(I_siit_supcod IN VARCHAR2,O_stscod OUT VARCHAR2,O_clsdat OUT DATE,O_annapprov OUT VARCHAR2)
IS
BEGIN
 SELECT spal_stscod , spal_clsdat , spal_annapprov INTO O_stscod , O_clsdat , O_annapprov FROM TSP_ALL WHERE spal_supcod = I_siit_supcod AND spal_sup = 'Y';
END PL_BLK5_SAVE2;
PROCEDURE PL_BLK5_SAVE3(I_stscod IN VARCHAR2,O_stsmsg OUT VARCHAR2)
IS
BEGIN
 SELECT apst_des INTO O_stsmsg FROM TAP_STSMST WHERE apst_module = 'SP' AND apst_stscod = I_stscod;
END PL_BLK5_SAVE3;
PROCEDURE PL_BLK5_SAVE4(I_siit_harmoncod IN VARCHAR2,O_i OUT NUMBER)
IS
BEGIN
 SELECT count ( 1 ) INTO O_i FROM tmp_harmonmst WHERE mphm_harmoncod = I_siit_harmoncod AND ( mphm_clsdat IS NULL OR mphm_clsdat > sysdate );
END PL_BLK5_SAVE4;
PROCEDURE PL_BLK5_SAVE5(I_siit_uom IN VARCHAR2,O_i OUT NUMBER)
IS
BEGIN
 SELECT count ( 1 ) INTO O_i FROM tmp_uommst WHERE mpum_uomcod = I_siit_uom AND ( mpum_clsdat IS NULL OR mpum_clsdat > sysdate );
END PL_BLK5_SAVE5;

PROCEDURE PL_NFI1(I_w_userid IN VARCHAR2,O_w_autgrp OUT VARCHAR2)
IS
BEGIN
 SELECT apus_autgrp INTO O_w_autgrp FROM tap_usrmst WHERE apus_userid = I_w_userid;
END PL_NFI1;
PROCEDURE PL_NFI2(I_w_autgrp IN VARCHAR2,I_w_module IN VARCHAR2,O_w_new OUT VARCHAR2,O_w_edit OUT VARCHAR2,O_w_enq OUT VARCHAR2,O_w_del OUT VARCHAR2)
IS
BEGIN
 SELECT apga_new , apga_edit , apga_enq , apga_del INTO O_w_new , O_w_edit , O_w_enq , O_w_del FROM tap_autgrpatt WHERE apga_autgrp = I_w_autgrp AND apga_module = I_w_module;
END PL_NFI2;
PROCEDURE PL_NFI3(I_g_userid IN VARCHAR2,I_g_module IN VARCHAR2,O_w_prt OUT VARCHAR2)
IS
BEGIN
 SELECT nvl ( apga_prt , 'N' ) INTO O_w_prt FROM tap_usrmst , tap_autgrpatt WHERE apus_userid = I_g_userid AND apus_autgrp = apga_autgrp AND apga_module = I_g_module;
END PL_NFI3;

PROCEDURE PL_TSIMST1_POQ1(I_sims_SHPMODCOD IN VARCHAR2,O_mpsm_des OUT VARCHAR2)
IS
BEGIN
 SELECT MPSM_DES INTO O_mpsm_des FROM tmp_shpmodmst WHERE MPSM_SHPMODCOD = I_sims_SHPMODCOD;
END PL_TSIMST1_POQ1;

PROCEDURE PL_TSIMST3_PRD1(I_SIMS_SAMINVRUN IN NUMBER,I_SIMS_DIVCOD IN VARCHAR2)
IS
BEGIN
 DELETE FROM TSI_DES WHERE SIDS_SAMINVRUN = I_SIMS_SAMINVRUN AND SIDS_DIVCOD = I_SIMS_DIVCOD;
END PL_TSIMST3_PRD1;
PROCEDURE PL_TSIMST3_PRD2(I_SIMS_SAMINVRUN IN NUMBER,I_SIMS_DIVCOD IN VARCHAR2)
IS
BEGIN
 DELETE FROM TSI_DES WHERE SIDS_SAMINVRUN = I_SIMS_SAMINVRUN AND SIDS_DIVCOD = I_SIMS_DIVCOD;
END PL_TSIMST3_PRD2;
PROCEDURE PL_TSIMST3_PRD3(I_SIMS_SAMINVRUN IN NUMBER,I_SIMS_DIVCOD IN VARCHAR2)
IS
BEGIN
 DELETE FROM TSI_DES WHERE SIDS_SAMINVRUN = I_SIMS_SAMINVRUN AND SIDS_DIVCOD = I_SIMS_DIVCOD;
END PL_TSIMST3_PRD3;
PROCEDURE PL_TSIMST3_PRD4(I_SIMS_SAMINVRUN IN NUMBER,I_SIMS_DIVCOD IN VARCHAR2)
IS
BEGIN
 DELETE FROM TSI_DES WHERE SIDS_SAMINVRUN = I_SIMS_SAMINVRUN AND SIDS_DIVCOD = I_SIMS_DIVCOD;
END PL_TSIMST3_PRD4;
PROCEDURE PL_TSIMST3_PRD5(I_SIMS_DIVCOD IN VARCHAR2,I_SIMS_SAMINVRUN IN NUMBER)
IS
BEGIN
 DELETE FROM TSI_ITM T WHERE T.SIIT_DIVCOD = I_SIMS_DIVCOD AND T.SIIT_SAMINVRUN = I_SIMS_SAMINVRUN;
END PL_TSIMST3_PRD5;

PROCEDURE PL_TSIMST3_PRI1(I_sims_dptcod IN VARCHAR2,I_g_divcod IN VARCHAR2,O_i OUT NUMBER)
IS
BEGIN
 SELECT count ( opdp_dptcod ) INTO O_i FROM top_dptmst WHERE opdp_divcod = I_g_divcod AND opdp_dptcod = I_sims_dptcod AND ( opdp_clsdat IS NULL OR opdp_clsdat > sysdate );
END PL_TSIMST3_PRI1;
PROCEDURE PL_TSIMST3_PRI2(I_sims_cuscod IN VARCHAR2,I_g_divcod IN VARCHAR2,O_i OUT NUMBER)
IS
BEGIN
 SELECT count ( cpms_cuscod ) INTO O_i FROM tcp_mst WHERE cpms_divcod = I_g_divcod AND cpms_cuscod = I_sims_cuscod AND ( cpms_clsdat IS NULL OR cpms_clsdat > sysdate ) AND upper ( cpms_stscod ) = 'AT';
END PL_TSIMST3_PRI2;
PROCEDURE PL_TSIMST3_PRI3(I_sims_cuscod IN VARCHAR2,I_sims_conpercod IN VARCHAR2,I_g_divcod IN VARCHAR2,O_i OUT NUMBER)
IS
BEGIN
 SELECT count ( cpcp_conpercod ) INTO O_i FROM tcp_conper WHERE cpcp_divcod = I_g_divcod AND cpcp_cuscod = I_sims_cuscod AND cpcp_conpercod = I_sims_conpercod AND ( cpcp_clsdat IS NULL OR cpcp_clsdat > sysdate );
END PL_TSIMST3_PRI3;
PROCEDURE PL_TSIMST3_PRI4(I_sims_port_l IN VARCHAR2,O_i OUT NUMBER)
IS
BEGIN
 SELECT count ( mpcp_port ) INTO O_i FROM tmp_ctypormst WHERE upper ( mpcp_port ) = upper ( I_sims_port_l ) AND ( mpcp_clsdat IS NULL OR mpcp_clsdat > sysdate );
END PL_TSIMST3_PRI4;
PROCEDURE PL_TSIMST3_PRI5(I_sims_via IN VARCHAR2,O_i OUT NUMBER)
IS
BEGIN
 SELECT count ( mpcp_port ) INTO O_i FROM tmp_ctypormst WHERE upper ( mpcp_port ) = upper ( I_sims_via ) AND ( mpcp_clsdat IS NULL OR mpcp_clsdat > sysdate );
END PL_TSIMST3_PRI5;
PROCEDURE PL_TSIMST3_PRI6(I_sims_port_d IN VARCHAR2,O_i OUT NUMBER)
IS
BEGIN
 SELECT count ( mpcp_port ) INTO O_i FROM tmp_ctypormst WHERE upper ( mpcp_port ) = upper ( I_sims_port_d ) AND ( mpcp_clsdat IS NULL OR mpcp_clsdat > sysdate );
END PL_TSIMST3_PRI6;
PROCEDURE PL_TSIMST3_PRI7(I_SIMS_ADRCOD IN VARCHAR2,I_SIMS_CUSCOD IN VARCHAR2,I_G_DIVCOD IN VARCHAR2,O_I OUT NUMBER)
IS
BEGIN
 SELECT COUNT ( * ) INTO O_I FROM TCP_MST , TCP_ADR WHERE CPMS_DIVCOD = I_G_DIVCOD AND CPMS_CUSCOD = CPAD_CUSCOD AND UPPER ( CPAD_ADRCOD ) = UPPER ( I_SIMS_ADRCOD ) AND UPPER ( CPAD_CUSCOD ) = UPPER ( I_SIMS_CUSCOD ) AND ( CPMS_CLSDAT IS NULL OR CPMS_CLSDAT > SYSDATE ) AND ( CPAD_CLSDAT IS NULL OR CPAD_CLSDAT > SYSDATE ) AND UPPER ( CPMS_STSCOD ) = 'AT';
END PL_TSIMST3_PRI7;
PROCEDURE PL_TSIMST3_PRI8(I_sims_carcod IN VARCHAR2,O_i OUT NUMBER)
IS
BEGIN
 SELECT count ( mpca_carcod ) INTO O_i FROM tmp_carmst WHERE upper ( mpca_carcod ) = upper ( I_sims_carcod ) AND ( mpca_clsdat IS NULL OR mpca_clsdat > sysdate );
END PL_TSIMST3_PRI8;
PROCEDURE PL_TSIMST3_PRI9(I_sims_shpmodcod IN VARCHAR2,O_i OUT NUMBER)
IS
BEGIN
 SELECT count ( MPSM_DES ) INTO O_i FROM tmp_shpmodmst WHERE upper ( mpsm_shpmodcod ) = upper ( I_sims_shpmodcod ) AND ( mpsm_clsdat IS NULL OR mpsm_clsdat > sysdate );
END PL_TSIMST3_PRI9;
PROCEDURE PL_TSIMST3_PRI10(I_info_div IN VARCHAR2,I_sims_dptcod IN VARCHAR2,O_sims_year OUT VARCHAR2)
IS
BEGIN
 SELECT opdp_invyy INTO O_sims_year FROM top_dptmst WHERE opdp_divcod = I_info_div AND opdp_dptcod = I_sims_dptcod AND ( opdp_clsdat IS NULL OR opdp_clsdat > sysdate );
END PL_TSIMST3_PRI10;
PROCEDURE PL_TSIMST3_PRI11(I_SIMS_TYP IN VARCHAR2,I_sims_dptcod IN VARCHAR2,I_sims_year IN VARCHAR2,O_sims_sernum OUT VARCHAR2)
IS
BEGIN
 SELECT lpad ( to_char ( nvl ( max ( to_number ( sims_sernum ) ) , 0 ) + 1 ) , 4 , '0' ) INTO O_sims_sernum FROM tsi_mst WHERE UPPER ( SIMS_TYP ) = UPPER ( I_SIMS_TYP ) AND upper ( sims_dptcod ) = upper ( I_sims_dptcod ) AND upper ( sims_year ) = upper ( I_sims_year );
END PL_TSIMST3_PRI11;
PROCEDURE PL_TSIMST3_PRI12(O_SIMS_SAMINVRUN OUT NUMBER)
IS
BEGIN
 SELECT SEQ_SIMS_RUNNUM.NEXTVAL INTO O_SIMS_SAMINVRUN FROM DUAL;
END PL_TSIMST3_PRI12;

PROCEDURE PL_TSIMST3_POQ1(I_sims_saminvrun IN NUMBER,O_lstchg OUT VARCHAR2)
IS
BEGIN
 SELECT to_char ( nvl ( SIMS_AMDDAT , crtdat ) , 'dd-MON-yyyy hh24:mi:ss' ) INTO O_lstchg FROM tsi_mst WHERE sims_saminvrun = I_sims_saminvrun;
END PL_TSIMST3_POQ1;
PROCEDURE PL_TSIMST3_POQ2(I_sims_saminvrun IN NUMBER,O_lstby OUT VARCHAR2)
IS
BEGIN
 SELECT nvl ( SIMS_AMDUSR , crtby ) INTO O_lstby FROM tsi_mst WHERE sims_saminvrun = I_sims_saminvrun;
END PL_TSIMST3_POQ2;

PROCEDURE PL_TSIMST3_PRU1(I_sims_dptcod IN VARCHAR2,I_g_divcod IN VARCHAR2,O_i OUT NUMBER)
IS
BEGIN
 SELECT count ( opdp_dptcod ) INTO O_i FROM top_dptmst WHERE opdp_divcod = I_g_divcod AND opdp_dptcod = I_sims_dptcod AND ( opdp_clsdat IS NULL OR opdp_clsdat > sysdate );
END PL_TSIMST3_PRU1;
PROCEDURE PL_TSIMST3_PRU2(I_sims_cuscod IN VARCHAR2,I_g_divcod IN VARCHAR2,O_i OUT NUMBER)
IS
BEGIN
 SELECT count ( cpms_cuscod ) INTO O_i FROM tcp_mst WHERE cpms_divcod = I_g_divcod AND cpms_cuscod = I_sims_cuscod AND ( cpms_clsdat IS NULL OR cpms_clsdat > sysdate ) AND upper ( cpms_stscod ) = 'AT';
END PL_TSIMST3_PRU2;
PROCEDURE PL_TSIMST3_PRU3(I_sims_cuscod IN VARCHAR2,I_sims_conpercod IN VARCHAR2,I_g_divcod IN VARCHAR2,O_i OUT NUMBER)
IS
BEGIN
 SELECT count ( cpcp_conpercod ) INTO O_i FROM tcp_conper WHERE cpcp_divcod = I_g_divcod AND cpcp_cuscod = I_sims_cuscod AND cpcp_conpercod = upper ( I_sims_conpercod ) AND ( cpcp_clsdat IS NULL OR cpcp_clsdat > sysdate );
END PL_TSIMST3_PRU3;
PROCEDURE PL_TSIMST3_PRU4(I_sims_port_l IN VARCHAR2,O_i OUT NUMBER)
IS
BEGIN
 SELECT count ( mpcp_port ) INTO O_i FROM tmp_ctypormst WHERE upper ( mpcp_port ) = upper ( I_sims_port_l ) AND ( mpcp_clsdat IS NULL OR mpcp_clsdat > sysdate );
END PL_TSIMST3_PRU4;
PROCEDURE PL_TSIMST3_PRU5(I_sims_via IN VARCHAR2,O_i OUT NUMBER)
IS
BEGIN
 SELECT count ( mpcp_port ) INTO O_i FROM tmp_ctypormst WHERE upper ( mpcp_port ) = upper ( I_sims_via ) AND ( mpcp_clsdat IS NULL OR mpcp_clsdat > sysdate );
END PL_TSIMST3_PRU5;
PROCEDURE PL_TSIMST3_PRU6(I_sims_port_d IN VARCHAR2,O_i OUT NUMBER)
IS
BEGIN
 SELECT count ( mpcp_port ) INTO O_i FROM tmp_ctypormst WHERE upper ( mpcp_port ) = upper ( I_sims_port_d ) AND ( mpcp_clsdat IS NULL OR mpcp_clsdat > sysdate );
END PL_TSIMST3_PRU6;
PROCEDURE PL_TSIMST3_PRU7(I_SIMS_ADRCOD IN VARCHAR2,I_SIMS_CUSCOD IN VARCHAR2,I_G_DIVCOD IN VARCHAR2,O_I OUT NUMBER)
IS
BEGIN
 SELECT COUNT ( * ) INTO O_I FROM TCP_MST , TCP_ADR WHERE CPMS_DIVCOD = I_G_DIVCOD AND CPMS_CUSCOD = CPAD_CUSCOD AND UPPER ( CPAD_ADRCOD ) = UPPER ( I_SIMS_ADRCOD ) AND UPPER ( CPAD_CUSCOD ) = UPPER ( I_SIMS_CUSCOD ) AND ( CPMS_CLSDAT IS NULL OR CPMS_CLSDAT > SYSDATE ) AND ( CPAD_CLSDAT IS NULL OR CPAD_CLSDAT > SYSDATE ) AND UPPER ( CPMS_STSCOD ) = 'AT';
END PL_TSIMST3_PRU7;
PROCEDURE PL_TSIMST3_PRU8(I_sims_carcod IN VARCHAR2,O_i OUT NUMBER)
IS
BEGIN
 SELECT count ( mpca_carcod ) INTO O_i FROM tmp_carmst WHERE upper ( mpca_carcod ) = upper ( I_sims_carcod ) AND ( mpca_clsdat IS NULL OR mpca_clsdat > sysdate );
END PL_TSIMST3_PRU8;
PROCEDURE PL_TSIMST3_PRU9(I_sims_shpmodcod IN VARCHAR2,O_i OUT NUMBER)
IS
BEGIN
 SELECT count ( MPSM_DES ) INTO O_i FROM tmp_shpmodmst WHERE upper ( mpsm_shpmodcod ) = upper ( I_sims_shpmodcod ) AND ( mpsm_clsdat IS NULL OR mpsm_clsdat > sysdate );
END PL_TSIMST3_PRU9;

PROCEDURE PL_TSIITM5_PRI1(I_SIIT_DIVCOD IN VARCHAR2,I_SIIT_SAMINVRUN IN NUMBER,O_SIIT_ITMSEQ OUT NUMBER)
IS
BEGIN
 SELECT nvl ( MAX ( SIIT_ITMSEQ ) , 0 ) + 1 INTO O_SIIT_ITMSEQ FROM TSI_ITM WHERE SIIT_DIVCOD = I_SIIT_DIVCOD AND SIIT_SAMINVRUN = I_SIIT_SAMINVRUN;
END PL_TSIITM5_PRI1;
PROCEDURE PL_TSIITM5_PRI2(I_siit_typ IN VARCHAR2,O_i OUT NUMBER)
IS
BEGIN
 SELECT count ( 1 ) INTO O_i FROM tsi_typ WHERE sity_typ = I_siit_typ;
END PL_TSIITM5_PRI2;
PROCEDURE PL_TSIITM5_PRI3(I_siit_supcod IN VARCHAR2,O_stscod OUT VARCHAR2,O_clsdat OUT DATE,O_annapprov OUT VARCHAR2)
IS
BEGIN
 SELECT spal_stscod , spal_clsdat , spal_annapprov INTO O_stscod , O_clsdat , O_annapprov FROM TSP_ALL WHERE spal_supcod = I_siit_supcod AND spal_sup = 'Y';
END PL_TSIITM5_PRI3;
PROCEDURE PL_TSIITM5_PRI4(I_stscod IN VARCHAR2,O_stsmsg OUT VARCHAR2)
IS
BEGIN
 SELECT apst_des INTO O_stsmsg FROM TAP_STSMST WHERE apst_module = 'SP' AND apst_stscod = I_stscod;
END PL_TSIITM5_PRI4;
PROCEDURE PL_TSIITM5_PRI5(I_siit_harmoncod IN VARCHAR2,O_i OUT NUMBER)
IS
BEGIN
 SELECT count ( 1 ) INTO O_i FROM tmp_harmonmst WHERE mphm_harmoncod = I_siit_harmoncod AND ( mphm_clsdat IS NULL OR mphm_clsdat > sysdate );
END PL_TSIITM5_PRI5;
PROCEDURE PL_TSIITM5_PRI6(I_siit_uom IN VARCHAR2,O_i OUT NUMBER)
IS
BEGIN
 SELECT count ( 1 ) INTO O_i FROM tmp_uommst WHERE mpum_uomcod = I_siit_uom AND ( mpum_clsdat IS NULL OR mpum_clsdat > sysdate );
END PL_TSIITM5_PRI6;

PROCEDURE PL_TSIITM5_PRU1(I_siit_typ IN VARCHAR2,O_i OUT NUMBER)
IS
BEGIN
 SELECT count ( 1 ) INTO O_i FROM tsi_typ WHERE sity_typ = I_siit_typ;
END PL_TSIITM5_PRU1;
PROCEDURE PL_TSIITM5_PRU2(I_siit_supcod IN VARCHAR2,O_stscod OUT VARCHAR2,O_clsdat OUT DATE,O_annapprov OUT VARCHAR2)
IS
BEGIN
 SELECT spal_stscod , spal_clsdat , spal_annapprov INTO O_stscod , O_clsdat , O_annapprov FROM TSP_ALL WHERE spal_supcod = I_siit_supcod AND spal_sup = 'Y';
END PL_TSIITM5_PRU2;
PROCEDURE PL_TSIITM5_PRU3(I_stscod IN VARCHAR2,O_stsmsg OUT VARCHAR2)
IS
BEGIN
 SELECT apst_des INTO O_stsmsg FROM TAP_STSMST WHERE apst_module = 'SP' AND apst_stscod = I_stscod;
END PL_TSIITM5_PRU3;
PROCEDURE PL_TSIITM5_PRU4(I_siit_harmoncod IN VARCHAR2,O_i OUT NUMBER)
IS
BEGIN
 SELECT count ( 1 ) INTO O_i FROM tmp_harmonmst WHERE mphm_harmoncod = I_siit_harmoncod AND ( mphm_clsdat IS NULL OR mphm_clsdat > sysdate );
END PL_TSIITM5_PRU4;
PROCEDURE PL_TSIITM5_PRU5(I_siit_uom IN VARCHAR2,O_i OUT NUMBER)
IS
BEGIN
 SELECT count ( 1 ) INTO O_i FROM tmp_uommst WHERE mpum_uomcod = I_siit_uom AND ( mpum_clsdat IS NULL OR mpum_clsdat > sysdate );
END PL_TSIITM5_PRU5;

PROCEDURE PL_TSIITM5_POQ1(I_siit_pr_runnum IN NUMBER,I_g_divcod IN VARCHAR2,O_case_no OUT VARCHAR2)
IS
BEGIN
 SELECT prms_caseno INTO O_case_no FROM vpr_mst WHERE prms_divcod = I_g_divcod AND prms_runnum = I_siit_pr_runnum AND EXISTS ( SELECT 1 FROM vap_dpt WHERE prms_divcod = opdv_divcod AND prms_dptcod = opdp_dptcod );
END PL_TSIITM5_POQ1;

PROCEDURE PL_UPDLST6_POQ1(I_SIMS_SAMINVRUN IN NUMBER,O_SIMS_INVNO OUT VARCHAR2)
IS
BEGIN
 SELECT SIMS_INVNO INTO O_SIMS_INVNO FROM V_TSI_MST WHERE SIMS_SAMINVRUN = I_SIMS_SAMINVRUN;
END PL_UPDLST6_POQ1;

PROCEDURE PL_CONTROL1_DELETE_BP1(I_sims_saminvrun IN NUMBER)
IS
BEGIN
 DELETE FROM tsi_des WHERE sids_saminvrun = I_sims_saminvrun;
END PL_CONTROL1_DELETE_BP1;
PROCEDURE PL_CONTROL1_DELETE_BP2(I_sims_saminvrun IN NUMBER)
IS
BEGIN
 DELETE FROM tsi_mst WHERE sims_saminvrun = I_sims_saminvrun;
END PL_CONTROL1_DELETE_BP2;

PROCEDURE PL_CONTROL1_COPY_BP1(I_w_autgrp IN VARCHAR2,I_w_module IN VARCHAR2,O_d_close OUT VARCHAR2)
IS
BEGIN
 SELECT apga_close INTO O_d_close FROM tap_autgrpatt WHERE apga_autgrp = I_w_autgrp AND apga_module = I_w_module;
END PL_CONTROL1_COPY_BP1;
PROCEDURE PL_CONTROL1_COPY_BP2(I_sims_saminvrun IN NUMBER,I_g_divcod IN VARCHAR2,O_rowcnt OUT NUMBER)
IS
BEGIN
 SELECT count ( * ) INTO O_rowcnt FROM tsi_des WHERE sids_divcod = I_g_divcod AND sids_saminvrun = I_sims_saminvrun AND sids_typ = 'DES';
END PL_CONTROL1_COPY_BP2;
PROCEDURE PL_CONTROL1_COPY_BP3(I_sims_saminvrun IN NUMBER,I_g_divcod IN VARCHAR2,O_rowcnt OUT NUMBER)
IS
BEGIN
 SELECT count ( * ) INTO O_rowcnt FROM tsi_des WHERE sids_divcod = I_g_divcod AND sids_saminvrun = I_sims_saminvrun AND sids_typ = 'BOT';
END PL_CONTROL1_COPY_BP3;
PROCEDURE PL_CONTROL1_COPY_BP4(I_sims_saminvrun IN NUMBER,I_g_divcod IN VARCHAR2,O_rowcnt OUT NUMBER)
IS
BEGIN
 SELECT count ( * ) INTO O_rowcnt FROM tsi_des WHERE sids_divcod = I_g_divcod AND sids_saminvrun = I_sims_saminvrun AND sids_typ = 'QTY';
END PL_CONTROL1_COPY_BP4;
PROCEDURE PL_CONTROL1_COPY_BP5(I_sims_saminvrun IN NUMBER,I_g_divcod IN VARCHAR2,O_rowcnt OUT NUMBER)
IS
BEGIN
 SELECT count ( * ) INTO O_rowcnt FROM tsi_des WHERE sids_divcod = I_g_divcod AND sids_saminvrun = I_sims_saminvrun AND sids_typ = 'AMT';
END PL_CONTROL1_COPY_BP5;
PROCEDURE PL_CONTROL1_COPY_BP6(I_sims_saminvrun IN NUMBER,I_g_divcod IN VARCHAR2,O_rowcnt OUT NUMBER)
IS
BEGIN
 SELECT count ( * ) INTO O_rowcnt FROM tsi_itm WHERE siit_divcod = I_g_divcod AND siit_saminvrun = I_sims_saminvrun;
END PL_CONTROL1_COPY_BP6;

PROCEDURE PL_CONTROL1_EDIT_BP1(I_sims_saminvrun IN NUMBER,I_g_divcod IN VARCHAR2,O_i OUT NUMBER)
IS
BEGIN
 SELECT count ( 1 ) INTO O_i FROM tsi_itm WHERE siit_divcod = I_g_divcod AND siit_saminvrun = I_sims_saminvrun;
END PL_CONTROL1_EDIT_BP1;

PROCEDURE PL_CRITERIA2_LOVPORTD_BP1(I_SIMS_PORT_D IN VARCHAR2,O_MPCP_NAM_D OUT VARCHAR2)
IS
BEGIN
 SELECT MPCP_NAM INTO O_MPCP_NAM_D FROM TMP_CTYPORMST WHERE UPPER ( MPCP_PORT ) = UPPER ( I_SIMS_PORT_D );
END PL_CRITERIA2_LOVPORTD_BP1;

PROCEDURE PL_CRITERIA2_MPSMDES_POT1(I_mpsm_des IN VARCHAR2,O_sims_shpmodcod OUT VARCHAR2)
IS
BEGIN
 SELECT MPSM_SHPMODCOD INTO O_sims_shpmodcod FROM TMP_SHPMODMST WHERE UPPER ( MPSM_DES ) = UPPER ( I_mpsm_des );
END PL_CRITERIA2_MPSMDES_POT1;

PROCEDURE PL_CRITERIA2_MPCPNAMD_POT1(I_MPCP_NAM_D IN VARCHAR2,O_SIMS_PORT_D OUT VARCHAR2)
IS
BEGIN
 SELECT mpcp_port INTO O_SIMS_PORT_D FROM TMP_CTYPORMST WHERE UPPER ( MPCP_NAM ) = UPPER ( I_MPCP_NAM_D );
END PL_CRITERIA2_MPCPNAMD_POT1;

PROCEDURE PL_RITERIA2_OVSIMSDIVCOD_BP1(I_sims_dptcod IN VARCHAR2,I_one IN VARCHAR2,O_i OUT INTEGER)
IS
BEGIN
 SELECT count ( * ) INTO O_i FROM top_dptmst WHERE opdp_divcod = I_one AND opdp_dptcod = I_sims_dptcod;
END PL_RITERIA2_OVSIMSDIVCOD_BP1;

PROCEDURE PL_CONTROL3_SAVE_BP1(I_sims_SAMINVRUN IN NUMBER,O_lstchg OUT VARCHAR2)
IS
BEGIN
 SELECT to_char ( nvl ( SIMS_AMDDAT , crtdat ) , 'dd-MON-yyyy hh24:mi:ss' ) INTO O_lstchg FROM tsi_mst WHERE sims_SAMINVRUN = I_sims_SAMINVRUN;
END PL_CONTROL3_SAVE_BP1;
PROCEDURE PL_CONTROL3_SAVE_BP2(I_sims_SAMINVRUN IN NUMBER,O_lstby OUT VARCHAR2)
IS
BEGIN
 SELECT nvl ( SIMS_AMDUSR , crtby ) INTO O_lstby FROM tsi_mst WHERE sims_SAMINVRUN = I_sims_SAMINVRUN;
END PL_CONTROL3_SAVE_BP2;

PROCEDURE PL_TSIMST3_SIMSCURCOD_VI1(I_sims_curcod IN VARCHAR2,O_N_COUNT OUT NUMBER)
IS
BEGIN
 SELECT COUNT ( '1' ) INTO O_N_COUNT FROM tmp_curmst WHERE mpcu_curcod = I_sims_curcod AND ( mpcu_clsdat IS NULL OR mpcu_clsdat > sysdate );
END PL_TSIMST3_SIMSCURCOD_VI1;
PROCEDURE PL_TSIMST3_SIMSCURCOD_VI2(I_sims_saminvrun IN NUMBER,I_g_divcod IN VARCHAR2,O_N_COUNT OUT NUMBER)
IS
BEGIN
 SELECT COUNT ( '1' ) INTO O_N_COUNT FROM tsi_itm WHERE siit_divcod = I_g_divcod AND siit_saminvrun = I_sims_saminvrun;
END PL_TSIMST3_SIMSCURCOD_VI2;

PROCEDURE PL_CONTROL5_OK_BP1(I_sims_saminvrun IN NUMBER,I_g_divcod IN VARCHAR2,O_sims_totamt OUT VARCHAR2,O_inv_amt OUT NUMBER)
IS
BEGIN
 SELECT ltrim ( to_char ( sum ( siit_totamt ) , '9,999,999,999,990.99' ) ) , sum ( siit_totamt ) INTO O_sims_totamt , O_inv_amt FROM tsi_itm WHERE siit_divcod = I_g_divcod AND siit_saminvrun = I_sims_saminvrun;
END PL_CONTROL5_OK_BP1;
PROCEDURE PL_CONTROL5_OK_BP2(I_sims_saminvrun IN NUMBER,I_g_divcod IN VARCHAR2,O_i OUT NUMBER)
IS
BEGIN
 SELECT count ( 1 ) INTO O_i FROM tsi_itm WHERE siit_divcod = I_g_divcod AND siit_saminvrun = I_sims_saminvrun;
END PL_CONTROL5_OK_BP2;

PROCEDURE PL_CONTROL5_SIITITMNUM_PC1(I_siit_itmnum IN VARCHAR2,I_g_divcod IN VARCHAR2,O_siit_harmoncod OUT VARCHAR2)
IS
BEGIN
 SELECT ipms_harmoncod INTO O_siit_harmoncod FROM tip_mst WHERE ipms_divcod = I_g_divcod AND ipms_itmnum = I_siit_itmnum;
END PL_CONTROL5_SIITITMNUM_PC1;

PROCEDURE PL_TSIITM5_SIITITMNUM_PC1(I_siit_itmnum IN VARCHAR2,I_g_divcod IN VARCHAR2,O_v_ipms_runnum OUT NUMBER,O_siit_harmoncod OUT VARCHAR2,O_siit_uom OUT VARCHAR2)
IS
BEGIN
 SELECT ipms_runnum , ipms_harmoncod , ipms_uombas INTO O_v_ipms_runnum , O_siit_harmoncod , O_siit_uom FROM tip_mst WHERE ipms_divcod = I_g_divcod AND ipms_itmnum = I_siit_itmnum;
END PL_TSIITM5_SIITITMNUM_PC1;
PROCEDURE PL_TSIITM5_SIITITMNUM_PC2(I_sims_curcod IN VARCHAR2,I_v_ipms_runnum IN NUMBER,I_sims_cuscod IN VARCHAR2,I_g_divcod IN VARCHAR2,O_siit_cusitm OUT VARCHAR2,O_siit_itmprc OUT NUMBER)
IS
BEGIN
 SELECT ipct_cusitm , decode ( cpms_curcod , I_sims_curcod , ipct_itmprc , to_number ( NULL ) ) INTO O_siit_cusitm , O_siit_itmprc FROM tip_cus , tcp_mst WHERE ipct_divcod = I_g_divcod AND ipct_itmrunnum = I_v_ipms_runnum AND ipct_cuscod = I_sims_cuscod AND ipct_divcod = cpms_divcod AND ipct_cuscod = cpms_cuscod AND rownum = 1;
END PL_TSIITM5_SIITITMNUM_PC2;
PROCEDURE PL_TSIITM5_SIITITMNUM_PC3(I_sims_curcod IN VARCHAR2,I_v_ipms_runnum IN NUMBER,I_g_divcod IN VARCHAR2,O_siit_cusitm OUT VARCHAR2,O_siit_itmprc OUT NUMBER)
IS
BEGIN
 SELECT ipct_cusitm , decode ( cpms_curcod , I_sims_curcod , ipct_itmprc , to_number ( NULL ) ) INTO O_siit_cusitm , O_siit_itmprc FROM tip_mst , tip_cus , tcp_mst WHERE ipct_divcod = I_g_divcod AND ipct_itmrunnum = I_v_ipms_runnum AND ipct_divcod = cpms_divcod AND ipct_cuscod = cpms_cuscod AND rownum = 1;
END PL_TSIITM5_SIITITMNUM_PC3;
PROCEDURE PL_TSIITM5_SIITITMNUM_PC4(I_v_ipms_runnum IN NUMBER,I_siit_supcod IN VARCHAR2,I_g_divcod IN VARCHAR2,O_siit_supcod OUT VARCHAR2,O_siit_supitm OUT VARCHAR2)
IS
BEGIN
 SELECT ipsu_supcod , ipsu_supitm INTO O_siit_supcod , O_siit_supitm FROM tip_sup WHERE ipsu_divcod = I_g_divcod AND ipsu_itmrunnum = I_v_ipms_runnum AND ipsu_supcod = I_siit_supcod AND rownum = 1;
END PL_TSIITM5_SIITITMNUM_PC4;
PROCEDURE PL_TSIITM5_SIITITMNUM_PC5(I_v_ipms_runnum IN NUMBER,I_g_divcod IN VARCHAR2,O_siit_supcod OUT VARCHAR2,O_siit_supitm OUT VARCHAR2)
IS
BEGIN
 SELECT ipsu_supcod , ipsu_supitm INTO O_siit_supcod , O_siit_supitm FROM tip_sup WHERE ipsu_divcod = I_g_divcod AND ipsu_itmrunnum = I_v_ipms_runnum AND rownum = 1;
END PL_TSIITM5_SIITITMNUM_PC5;
PROCEDURE PL_TSIITM5_SIITITMNUM_PC6(I_v_ipms_runnum IN NUMBER,I_g_divcod IN VARCHAR2,O_siit_itmdes OUT VARCHAR2)
IS
BEGIN
 SELECT ipds_des INTO O_siit_itmdes FROM tip_des WHERE ipds_divcod = I_g_divcod AND ipds_itmrunnum = I_v_ipms_runnum AND ipds_typ = 'PRM';
END PL_TSIITM5_SIITITMNUM_PC6;

PROCEDURE PL_TSIITM5_SIITSUPCOD_POT1(I_siit_itmnum IN VARCHAR2,I_siit_supcod IN VARCHAR2,I_g_divcod IN VARCHAR2,O_siit_supitm OUT VARCHAR2)
IS
BEGIN
 SELECT ipsu_supitm INTO O_siit_supitm FROM tip_mst , tip_sup WHERE ipms_divcod = I_g_divcod AND ipms_itmnum = I_siit_itmnum AND ipms_divcod = ipsu_divcod AND ipms_runnum = ipsu_itmrunnum AND ipsu_supcod = I_siit_supcod AND rownum = 1;
END PL_TSIITM5_SIITSUPCOD_POT1;
PROCEDURE PL_TSIITM5_SIITSUPCOD_POT2(I_siit_itmnum IN VARCHAR2,I_g_divcod IN VARCHAR2,O_siit_supitm OUT VARCHAR2)
IS
BEGIN
 SELECT ipsu_supitm INTO O_siit_supitm FROM tip_mst , tip_sup WHERE ipms_divcod = I_g_divcod AND ipms_itmnum = I_siit_itmnum AND ipms_divcod = ipsu_divcod AND ipms_runnum = ipsu_itmrunnum AND rownum = 1;
END PL_TSIITM5_SIITSUPCOD_POT2;

PROCEDURE PL_TSIITM5_CASENO_POT1(I_case_no IN VARCHAR2,I_g_divcod IN VARCHAR2,O_siit_pr_runnum OUT NUMBER)
IS
BEGIN
 SELECT prms_runnum INTO O_siit_pr_runnum FROM vpr_mst WHERE prms_divcod = I_g_divcod AND prms_caseno = I_case_no AND EXISTS ( SELECT 1 FROM vap_dpt WHERE prms_divcod = opdv_divcod AND prms_dptcod = opdp_dptcod );
END PL_TSIITM5_CASENO_POT1;

PROCEDURE PL_TSIITM5_LOVSIITSUPCOD_BP1(I_siit_itmnum IN VARCHAR2,I_siit_supcod IN VARCHAR2,I_g_divcod IN VARCHAR2,O_siit_supitm OUT VARCHAR2)
IS
BEGIN
 SELECT ipsu_supitm INTO O_siit_supitm FROM tip_mst , tip_sup WHERE ipms_divcod = I_g_divcod AND ipms_itmnum = I_siit_itmnum AND ipms_divcod = ipsu_divcod AND ipms_runnum = ipsu_itmrunnum AND ipsu_supcod = I_siit_supcod AND rownum = 1;
END PL_TSIITM5_LOVSIITSUPCOD_BP1;
PROCEDURE PL_TSIITM5_LOVSIITSUPCOD_BP2(I_siit_itmnum IN VARCHAR2,I_g_divcod IN VARCHAR2,O_siit_supitm OUT VARCHAR2)
IS
BEGIN
 SELECT ipsu_supitm INTO O_siit_supitm FROM tip_mst , tip_sup WHERE ipms_divcod = I_g_divcod AND ipms_itmnum = I_siit_itmnum AND ipms_divcod = ipsu_divcod AND ipms_runnum = ipsu_itmrunnum AND rownum = 1;
END PL_TSIITM5_LOVSIITSUPCOD_BP2;

PROCEDURE PL_CONTROL6_PRTEXCEL_BP1(O_line_buffer OUT VARCHAR2)
IS
BEGIN
 SELECT to_char ( sysdate , 'DD-MON-YYYY' ) INTO O_line_buffer FROM dual;
END PL_CONTROL6_PRTEXCEL_BP1;
PROCEDURE PL_CONTROL6_PRTEXCEL_BP2(O_temp_string OUT VARCHAR2)
IS
BEGIN
 SELECT to_char ( sysdate , 'HH:MI:SS' ) INTO O_temp_string FROM dual;
END PL_CONTROL6_PRTEXCEL_BP2;
PROCEDURE PL_CONTROL6_PRTEXCEL_BP3(O_col_used OUT NUMBER)
IS
BEGIN
 SELECT count ( 1 ) INTO O_col_used FROM tdr_template , tdr_tpl_col , tdr_col WHERE drtp_rptcod = 'SIVREP' AND drtp_tplnam = 'Sample Invoice Report' AND drtp_rptcod = drtc_rptcod AND drtp_runnum = drtc_runnum AND drtc_print = 'Y' AND drtc_rptcod = drcl_rptcod AND drtc_col = drcl_col;
END PL_CONTROL6_PRTEXCEL_BP3;

PROCEDURE PL_ITMRMKITMRMK_REMARKOK_BP1(I_mprm_rmkcod IN VARCHAR2,I_g_divcod IN VARCHAR2,O_siit_itmdes OUT VARCHAR2)
IS
BEGIN
 SELECT mprm_des INTO O_siit_itmdes FROM tmp_rmkmst WHERE mprm_divcod = I_g_divcod AND mprm_rmkcod = I_mprm_rmkcod;
END PL_ITMRMKITMRMK_REMARKOK_BP1;

PROCEDURE PL_PRINTPRINT_PRINTOK_BP1(I_w_div IN VARCHAR2,I_sims_invno_from IN VARCHAR2,I_sims_invno_to IN VARCHAR2,I_sims_clsdat IN VARCHAR2,O_vcount OUT NUMBER)
IS
BEGIN
 SELECT count ( * ) INTO O_vcount FROM v_tsi_mst WHERE sims_divcod = I_w_div AND ( sims_invno >= I_sims_invno_from OR I_sims_invno_from IS NULL ) AND ( sims_invno <= I_sims_invno_to OR I_sims_invno_to IS NULL ) AND ( ( nvl ( I_sims_clsdat , 'N' ) = 'N' AND nvl ( sims_clsdat , to_date ( '01/01/2222' , 'dd/mm/yyyy' ) ) > sysdate ) OR nvl ( I_sims_clsdat , 'N' ) = 'Y' );
END PL_PRINTPRINT_PRINTOK_BP1;

PROCEDURE PL_REMARKREMARK_REMARKOK_BP1(I_sims_saminvrun IN NUMBER,O_sids_des OUT LONG)
IS
BEGIN
 SELECT sids_des INTO O_sids_des FROM tsi_des WHERE sids_saminvrun = I_sims_saminvrun AND sids_typ = 'DES';
END PL_REMARKREMARK_REMARKOK_BP1;
PROCEDURE PL_REMARKREMARK_REMARKOK_BP2(I_sims_saminvrun IN NUMBER,O_sids_des OUT LONG)
IS
BEGIN
 SELECT sids_des INTO O_sids_des FROM tsi_des WHERE sids_saminvrun = I_sims_saminvrun AND sids_typ = 'BOT';
END PL_REMARKREMARK_REMARKOK_BP2;
PROCEDURE PL_REMARKREMARK_REMARKOK_BP3(I_sims_saminvrun IN NUMBER,O_sids_des OUT LONG)
IS
BEGIN
 SELECT sids_des INTO O_sids_des FROM tsi_des WHERE sids_saminvrun = I_sims_saminvrun AND sids_typ = 'QTY';
END PL_REMARKREMARK_REMARKOK_BP3;
PROCEDURE PL_REMARKREMARK_REMARKOK_BP4(I_sims_saminvrun IN NUMBER,O_sids_des OUT LONG)
IS
BEGIN
 SELECT sids_des INTO O_sids_des FROM tsi_des WHERE sids_saminvrun = I_sims_saminvrun AND sids_typ = 'AMT';
END PL_REMARKREMARK_REMARKOK_BP4;
PROCEDURE PL_REMARKREMARK_REMARKOK_BP5(I_mprm_rmkcod IN VARCHAR2,I_g_divcod IN VARCHAR2,O_sids_des OUT LONG)
IS
BEGIN
 SELECT mprm_des INTO O_sids_des FROM tmp_rmkmst WHERE mprm_divcod = I_g_divcod AND mprm_rmkcod = I_mprm_rmkcod AND ( mprm_clsdat IS NULL OR mprm_clsdat > sysdate );
END PL_REMARKREMARK_REMARKOK_BP5;
PROCEDURE PL_REMARKREMARK_REMARKOK_BP6(I_mprm_rmkcod IN VARCHAR2,I_g_divcod IN VARCHAR2,O_sids_des OUT LONG)
IS
BEGIN
 SELECT mprm_des INTO O_sids_des FROM tmp_rmkmst WHERE mprm_divcod = I_g_divcod AND mprm_rmkcod = I_mprm_rmkcod AND ( mprm_clsdat IS NULL OR mprm_clsdat > sysdate );
END PL_REMARKREMARK_REMARKOK_BP6;
PROCEDURE PL_REMARKREMARK_REMARKOK_BP7(I_mprm_rmkcod IN VARCHAR2,I_g_divcod IN VARCHAR2,O_sids_des OUT LONG)
IS
BEGIN
 SELECT mprm_des INTO O_sids_des FROM tmp_rmkmst WHERE mprm_divcod = I_g_divcod AND mprm_rmkcod = I_mprm_rmkcod AND ( mprm_clsdat IS NULL OR mprm_clsdat > sysdate );
END PL_REMARKREMARK_REMARKOK_BP7;
PROCEDURE PL_REMARKREMARK_REMARKOK_BP8(I_mprm_rmkcod IN VARCHAR2,I_g_divcod IN VARCHAR2,O_sids_des OUT LONG)
IS
BEGIN
 SELECT mprm_des INTO O_sids_des FROM tmp_rmkmst WHERE mprm_divcod = I_g_divcod AND mprm_rmkcod = I_mprm_rmkcod AND ( mprm_clsdat IS NULL OR mprm_clsdat > sysdate );
END PL_REMARKREMARK_REMARKOK_BP8;

PROCEDURE PL_SAMSAM_SAMOK_BP1(I_sam_dptcod IN VARCHAR2,I_sims_divcod IN VARCHAR2,I_g_divcod IN VARCHAR2,I_g_userid IN VARCHAR2,O_i OUT NUMBER)
IS
BEGIN
 SELECT count ( * ) INTO O_i FROM top_dptmst WHERE opdp_divcod = I_g_divcod AND opdp_dptcod = I_sam_dptcod AND ( opdp_clsdat IS NULL OR trunc ( opdp_clsdat ) > trunc ( sysdate ) ) AND check_dpt ( opdp_dptcod , nvl ( I_sims_divcod , I_g_divcod ) , I_g_userid ) = 'true';
END PL_SAMSAM_SAMOK_BP1;
PROCEDURE PL_SAMSAM_SAMOK_BP2(I_sam_year IN VARCHAR2,O_sam_year OUT VARCHAR2)
IS
BEGIN
 SELECT lpad ( I_sam_year , 2 , '0' ) INTO O_sam_year FROM dual;
END PL_SAMSAM_SAMOK_BP2;

END DP_MIG_XSIM2;

