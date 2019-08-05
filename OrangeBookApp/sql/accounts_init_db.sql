-- Account INIT DB
ALTER TABLE public.account DROP CONSTRAINT IF EXISTS account_iban ;
ALTER TABLE public.account ADD CONSTRAINT account_iban UNIQUE (account_iban);

INSERT INTO public.account(
	id, account_iban, balance, name)
	VALUES 
	(1, 'ES9820385778983000760236', 1000.00, 'Luis Amesty Linares'),
	(2, 'ES9820385778983000760234', 12000.00, 'Maria Auxiliadora Amesty'),
	(3, 'ES9820385778983000760230', 10000.00, 'Maria Alejandra Amesty'),
	(4, 'ES9820385778983000760240', 8000.00, 'Luis Amesty Morello'),
	(5, 'ES9820385778983000760238', 7000.00, 'Maria Virginia Linares') ;
