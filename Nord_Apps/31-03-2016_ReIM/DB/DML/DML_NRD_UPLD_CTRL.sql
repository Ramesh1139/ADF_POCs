-- Change the URL according to the Environment

INSERT INTO nrd_upld_ctrl
   SELECT store, 'RMS13', 'http://y0319t971.nordstrom.net:8021'
     FROM store@rms13n1.nordstrom.com
   UNION ALL
   SELECT wh, 'RMS13', 'http://y0319t971.nordstrom.net:8021'
     FROM wh@rms13n1.nordstrom.com;
COMMIT;