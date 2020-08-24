$('#confirmacaoExclusaoModal').on('show.bs.modal', function(event)
{
	var button = $(event.relatedTarget);
	var codigoVeiculo = button.data('codigo');
	var descricaoVeiculo = button.data('descricao');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	
	if(!action.endsWith('/')) { action += '/';}
	
	form.attr('action', action + codigoVeiculo);
	
	modal.find('.modal-body p').html('Tem certeza que deseja excluir o veículo <strong>' + descricaoVeiculo + '</strong>?');
});

$(document).ready(function()
{
	$('[rel="tooltip"]').tooltip({container : 'body', trigger : 'hover', placement : "top"});
	$('.js-currency').maskMoney({decimal: ',', thousands: '.', allowZero: true});
	
	$('.js-atualizar-status').on('click', function(event)
	{
		event.preventDefault();
		
		var botaoReceber = $(event.currentTarget);
		var urlReceber = botaoReceber.attr('href');
		
		var response = $.ajax(
		{
			url: urlReceber,
			type: 'PUT'
		});
		
		response.done(function(e)
		{
			var codigoVeiculo = botaoReceber.data('codigo');
			$('[data-role=' + codigoVeiculo + ']').html('<span class="badge badge-success" th:text="${veiculo.vendido.descricao}">' + e + '</span>');
			botaoReceber.hide();
		});
		
		response.fail(function(e)
		{
			console.log(e);
			alert('Erro ao receber cobrança!');
		});
	});
});