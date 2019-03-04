#mybatis知识点
 1. 批量插入返回自增主键
    >
         <insert id="insertBatch" parameterType="java.util.List"
              useGeneratedKeys="true">
              insert into credit_st_account_statement (sign_md5, company_name,
                org_no, merchant_no, account_no,
                account_name, statement_no, trade_date,
                income, pay, balance,
                currency, memo, other_account_no,
                other_deposit_bank, accounting_date, remarks,
                trade_serial_number, ent_serial_number, voucher_type,
                voucher_no, trade_bank, other_province,
                other_account_name, trade_type, trade_status,
                creater, modifier, created_date,
                modified_date)
              values
              <foreach collection="list" item="st" index="index" separator=",">
                (#{st.signMd5,jdbcType=VARCHAR}, #{st.companyName,jdbcType=VARCHAR},
                #{st.orgNo,jdbcType=VARCHAR}, #{st.merchantNo,jdbcType=VARCHAR}, #{st.accountNo,jdbcType=VARCHAR},
                #{st.accountName,jdbcType=VARCHAR}, #{st.statementNo,jdbcType=VARCHAR}, #{st.tradeDate,jdbcType=TIMESTAMP},
                #{st.income,jdbcType=DECIMAL}, #{st.pay,jdbcType=DECIMAL}, #{st.balance,jdbcType=DECIMAL},
                #{st.currency,jdbcType=VARCHAR}, #{st.memo,jdbcType=VARCHAR}, #{st.otherAccountNo,jdbcType=VARCHAR},
                #{st.otherDepositBank,jdbcType=VARCHAR}, #{st.accountingDate,jdbcType=TIMESTAMP}, #{st.remarks,jdbcType=VARCHAR},
                #{st.tradeSerialNumber,jdbcType=VARCHAR}, #{st.entSerialNumber,jdbcType=VARCHAR}, #{st.voucherType,jdbcType=VARCHAR},
                #{st.voucherNo,jdbcType=VARCHAR}, #{st.tradeBank,jdbcType=VARCHAR}, #{st.otherProvince,jdbcType=VARCHAR},
                #{st.otherAccountName,jdbcType=VARCHAR}, #{st.tradeType,jdbcType=VARCHAR}, #{st.tradeStatus,jdbcType=VARCHAR},
                #{st.creater,jdbcType=VARCHAR}, #{st.modifier,jdbcType=VARCHAR}, NOW(),
                NOW())
              </foreach>
            </insert>
        
    **注释：mybatis版本需要升级到3.3.1**
    
 2.mybatis分页插件
  

    
    