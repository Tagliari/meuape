
<div class="box">
	<div class="box-header">
		<h3 class="box-title">Pagamentos Gerados - (Boleto)</h3>
	</div>

	<div class="box-body no-padding">
		<table class="table table-striped">
			<tbody>
				<tr>
					<th>Id</th>
					<th>Cliente</th>
					<th>Localidade</th>
					<th>Tipo</th>
					<th>Pagamento</th>
					<th>Total</th>
					<th>Status</th>
				</tr>

				<c:forEach items="${allPaymentBoleto}" var="payment">
					<tr>
						<td>${payment.id}</td>
						<td>
							<b>${payment.userObject.displayName}</br>
							${payment.userObject.email}</br></b>
							<c:choose>
								<c:when test="${not empty payment.userObject.cpf }">
									${payment.userObject.cpf}
								</c:when>
								<c:otherwise>
									${payment.userObject.cnpj}
								</c:otherwise>
							</c:choose>
							</br>
							${payment.userObject.accountType}</br>
							${payment.userObject.phone}
						</td>
						<td>
							${payment.appartment.building.displayName}&nbsp;-&nbsp;N&nbsp;${payment.appartment.building.number}</br>
							${payment.appartment.building.neighborhood}&nbsp;-&nbsp;${payment.appartment.building.zipcode}</br>
							${payment.appartment.building.street}&nbsp;AP&nbsp;-&nbsp;${payment.appartment.number}</br>
							${payment.appartment.building.country}&nbsp;-&nbsp;${payment.appartment.building.state}&nbsp;-&nbsp;${payment.appartment.building.city}</br>
						</td>
						<td>${payment.type}</td>
						<td>
							<a href="${payment.gerencianetBoletoPaymentObject.gnBoletoLink}" target="_BLANK" class="btn btn-block btn-default btn-sm">Baixar&nbsp;Boleto</a></br>
							
							<div style="display:none;" id="${payment.id}">${payment.gerencianetBoletoPaymentObject.gnBoletoBarcode}</div>
							<button type="button" class="btn btn-block btn-default btn-sm" onclick="copyToClipboard(document.getElementById('${payment.id}'));">Copiar&nbsp;Barcode</button>
						</td>
						<td><b><fmt:formatNumber value="${payment.amount}" type="currency" /></b></td>
						<td>
							<c:choose>
								<c:when test="${payment.gerencianetBoletoPaymentObject.gnBoletoStatus eq 'waiting'}">
									<span class="label label-info">Aguardando&nbsp;Pagamento</span></br>
								</c:when>
								<c:when test="${payment.gerencianetBoletoPaymentObject.gnBoletoStatus eq 'paid'}">
									<span class="label label-success">Pagamento&nbsp;Realizado</span></br>
								</c:when>
								<c:when test="${payment.gerencianetBoletoPaymentObject.gnBoletoStatus eq 'unpaid'}">
									<span class="label label-error">Pagamento&nbsp;N&atilde;oRealizado</span></br>
								</c:when>
								<c:otherwise>
									<span class="label label-warning">Boleto&nbsp;N&atilde;o&nbsp;Gerado</span></br>
								</c:otherwise>
							</c:choose>
							
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
