INSERT INTO public.accounttransaction(
	id, account_iban, amount, date, fee, reference, trdescription, trstatus)
	VALUES 
	(1, 'ES9820385778983000760236-A', 1100, '2019-07-16 16:55:42.000',  2, '12345A', 'Restaurant payment', 'OK'),
	(2, 'ES9820385778983000760236-B', 1200,'2019-07-16 16:55:42.000',  2, '12345A', 'Boutique payment', 'OK')