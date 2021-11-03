-- ****************************************************************************
-- Script Name          : n5rim05.sql
-- Language/Shell       : PL/SQL
-- Description          : The sql block calls the function in package
--                        PKG_REIM_UPLD.FN_CALC_DATA
--
-- Returns              : None
-- Return Values        : None
--
-- Called from scripts/ : n5gencall.ksh
-- programs(if any)     :
--
-- Execute Mode
-- (batch/interactive)  : Interactive
--
-- Author               : Bikash, Infosys Ltd.
-- Date written         : 26-Feb-2015
--
-- Modification History :
--
-- Description of change                 Date           Modified by
-- ---------------------                 ----           -----------
-- ****************************************************************************
DECLARE
   L_error_message      RTK_ERRORS.RTK_TEXT%TYPE;
BEGIN
   :ERROR_CODE:=0;
   -- Calling the function to create POSU file
   IF NOT PKG_REIM_UPLD.FN_CALC_DATA(L_error_message) THEN
      DBMS_OUTPUT.PUT_LINE ('Function Error Message :'|| SUBSTR(L_error_message ,1,235));
      :ERROR_CODE:=255;
   ELSE
      DBMS_OUTPUT.PUT_LINE ('Function executed successfully');
   END IF;

EXCEPTION
   WHEN OTHERS THEN
      ROLLBACK;
      DBMS_OUTPUT.PUT_LINE('Function Error Message : '|| SQLERRM);
      NRD_WRITE_CUSTOM_LOG(SQLCODE,
                           SQLERRM,
                           'Failed In Insert Service',
                           'Job Name : n5rim05');
      :ERROR_CODE:=255;
END;
/
