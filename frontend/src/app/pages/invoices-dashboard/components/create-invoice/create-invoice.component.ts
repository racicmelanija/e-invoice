import { Component, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { Select } from '@ngxs/store';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';
import { InvoiceItem } from 'src/app/models/invoice-item.model';
import { CollaborationService } from 'src/app/services/collaboration.service';
import { CompanyService } from 'src/app/services/company.service';
import { InvoiceService } from 'src/app/services/invoice.service';
import { AppState } from 'src/app/shared/app.state';

@Component({
  selector: 'app-create-invoice',
  templateUrl: './create-invoice.component.html',
  styleUrls: ['./create-invoice.component.scss']
})
export class CreateInvoiceComponent implements OnInit {
  createInvoiceForm!: FormGroup;
  @Select(AppState.getCompanyId) companyId$: Observable<String> | undefined;
  items: InvoiceItem[] = [];
  companyTaxId: any;
  clients: any[] = [];
  bankAccount: any;
  total: number = 0;

  constructor(private invoiceService: InvoiceService, private collaborationService: CollaborationService, private companyService: CompanyService, private toastr: ToastrService) { }

  ngOnInit(): void {
    this.initializeForm();
  }

  private initializeForm(): void {
    let companyId: String = "";
    this.companyId$?.subscribe(
      data => {
        companyId = data;
        this.collaborationService.getClients(companyId, 0, 1000).subscribe(
          data => {
            this.clients = data.collaborations;
          }
        )
        this.companyService.getCompany(companyId).subscribe(
          data => {
            this.bankAccount = data.bankAccount;
            this.companyTaxId = data.taxId;  
          }
        )
      }
    )

    this.createInvoiceForm = new FormGroup({
      client: new FormControl('', [Validators.required]),
      bankAccount: new FormControl('', [Validators.required]),
      referenceNumber: new FormControl('', [Validators.required]),
      invoiceItems: new FormArray([this.newItem()], [Validators.required])
    });
  }

  onSubmit() {
    let companyId;
    this.companyId$?.subscribe(
      data => {
        companyId = data
      }
    )

    let invoice = {
      companyId: companyId, 
      companyTaxId: this.companyTaxId,
      clientId: this.createInvoiceForm.get("client")?.value.clientId,
      clientTaxId: this.createInvoiceForm.get("client")?.value.clientTaxIdentificationNumber,
      bankAccount: this.createInvoiceForm.get("bankAccount")?.value,
      referenceNumber: this.createInvoiceForm.get("referenceNumber")?.value,
      invoiceItems: this.createInvoiceForm.get("invoiceItems")?.value,
      total: this.total
    }
    this.invoiceService.postInvoice(invoice).subscribe(
      data => {
        this.toastr.success("Successfully sent invoice");
        this.createInvoiceForm.reset();
      }, error => {
        this.toastr.error(error.error.errorMessage, "Error");
      }
    );
  }

  newItem() : FormGroup {
    return new FormGroup ({
      name: new FormControl('', [Validators.required]),
      unitOfMeasure: new FormControl('', [Validators.required]),
      unitPrice: new FormControl('', [Validators.required]),
      quantity: new FormControl('', [Validators.required]),
      discount: new FormControl('', [Validators.required]),
      taxPercent: new FormControl('', [Validators.required])
    })
  }

  addItem() {
      this.invoiceItems().push(this.newItem());
  }

  invoiceItems() : FormArray {
    return this.createInvoiceForm.get("invoiceItems") as FormArray
  }

  updateTotal() {
    if(this.createInvoiceForm.valid) {
      this.total = 0;
      let items = this.createInvoiceForm.get("invoiceItems")?.value;
      items.forEach((item: any) => {
        let baseTotal = (item.unitPrice * item.quantity)*((100-item.discount)/100);
        this.total += baseTotal + baseTotal*(item.taxPercent/100);
      });
    }
  }
}
