import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class InvoiceService {
  baseUrl: string = environment.baseUrl + '/invoice-service/invoices'

  constructor(private http: HttpClient) { }

  getOutgoingInvoices(companyId: String, page: number, size: number): Observable<any> {
    return this.http.get(this.baseUrl + "/outgoing?companyId=" + companyId + "&page=" + page + "&size=" + size);
  }

  getIncomingInvoices(clientId: String, page: number, size: number): Observable<any> {
    return this.http.get(this.baseUrl + "/incoming?clientId=" + clientId + "&page=" + page + "&size=" + size);
  }

  postInvoice(invoice: any): Observable<any> {
    return this.http.post(this.baseUrl, invoice);
  }

  updateInvoice(invoice: any): Observable<any> {
    return this.http.put(this.baseUrl, invoice);
  }
}
