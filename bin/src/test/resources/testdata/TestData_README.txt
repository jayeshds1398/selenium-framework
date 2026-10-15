TEST DATA FILES - README
========================

This folder contains Excel (.xlsx) test data files used for Data-Driven Testing.

FILES REQUIRED:
---------------

1. LoginData.xlsx
   Sheet Name: LoginTests
   Columns:
   | TC_ID     | Username  | Password  | ExpectedResult  |
   |-----------|-----------|-----------|-----------------|
   | LOGIN_006 | Admin     | admin123  | Dashboard       |
   | LOGIN_007 | Admin123  | admin123  | Error Message   |
   | LOGIN_008 | Admin     | wrong123  | Error Message   |
   | LOGIN_009 |           | admin123  | Validation Msg  |
   | LOGIN_010 | Admin     |           | Validation Msg  |
   | LOGIN_011 |           |           | Validation Msg  |

2. ForgotPasswordData.xlsx
   Sheet Name: ForgotPasswordTests
   Columns:
   | TC_ID  | Username     | ExpectedResult          |
   |--------|--------------|-------------------------|
   | FP_008 | Admin        | Success Message         |
   | FP_009 | invalidUser  | Error/Validation Msg    |
   | FP_010 |              | Required Field Msg      |
   | FP_012 | Admin        | Accepted                |

NOTE: Create these Excel files manually using Microsoft Excel or LibreOffice Calc.
The ExcelUtils.java class will read data from these files automatically at runtime.
