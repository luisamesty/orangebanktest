-- AccountTransactions INIT DB
ALTER TABLE public.accounttransaction DROP CONSTRAINT IF EXISTS accounttransaction_reference;
ALTER TABLE public.accounttransaction 
ADD CONSTRAINT accounttransaction_reference UNIQUE (reference, account_iban);

ALTER TABLE public.accounttransaction DROP CONSTRAINT IF EXISTS accounttransaction_accountiban;
ALTER TABLE public.accounttransaction 
ADD CONSTRAINT accounttransaction_accountiban FOREIGN KEY (account_iban)
        REFERENCES public.account (account_iban) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION ;
