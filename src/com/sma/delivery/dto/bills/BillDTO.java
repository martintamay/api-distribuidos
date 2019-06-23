package com.sma.delivery.dto.bills;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sma.delivery.dto.base.BaseDTO;
import com.sma.delivery.dto.bills_details.BillDetailDTO;
@XmlRootElement(name = "bill")
public class BillDTO extends BaseDTO{
	private static final long serialVersionUID = 1L;
	
	@XmlElement
	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
	
	@XmlElement
	public Integer getIva10() {
		return iva10;
	}

	public void setIva10(Integer iva10) {
		this.iva10 = iva10;
	}
	
	@XmlElement
	public Integer getIva5() {
		return iva5;
	}

	public void setIva5(Integer iva5) {
		this.iva5 = iva5;
	}
	
	@XmlElement
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	@XmlElement
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@XmlElement
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@XmlElement
	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	@XmlElement
	public String getTimbrado() {
		return timbrado;
	}

	public void setTimbrado(String timbrado) {
		this.timbrado = timbrado;
	}

	@XmlElement
	public Integer getNum1() {
		return num1;
	}

	public void setNum1(Integer num1) {
		this.num1 = num1;
	}

	@XmlElement
	public Integer getNum2() {
		return num2;
	}

	public void setNum2(Integer num2) {
		this.num2 = num2;
	}

	@XmlElement
	public Integer getNum3() {
		return num3;
	}

	public void setNum3(Integer num3) {
		this.num3 = num3;
	}

	@XmlElement
	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	@XmlElement
	public Set<BillDetailDTO> getBillsDetails() {
		return billsDetails;

	}
	
	public void setBillsDetails(Set<BillDetailDTO> billsDetails) {
		this.billsDetails = billsDetails;
	}


	private Set<BillDetailDTO> billsDetails = new HashSet<>();
	private String total;
	private Integer iva10;
	private Integer iva5;
	private String nombre;
	private String direccion;
	private String ruc;
	private String timbrado;
	private Integer num1;
	private Integer num2;
	private Integer num3;
	private String fecha;
	private Integer orderId;
}

