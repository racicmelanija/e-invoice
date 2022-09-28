import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { AddRoleDialogComponent } from 'src/app/pages/users-dashboard/components/add-role-dialog/add-role-dialog.component';
import { InvoiceService } from 'src/app/services/invoice.service';

@Component({
  selector: 'app-update-invoice-dialog',
  templateUrl: './update-invoice-dialog.component.html',
  styleUrls: ['./update-invoice-dialog.component.scss']
})
export class UpdateInvoiceDialogComponent implements OnInit {
  invoiceId: string = '';
  updateInvoiceForm!: FormGroup;

  constructor(private dialogRef: MatDialogRef<AddRoleDialogComponent>, @Inject(MAT_DIALOG_DATA) data: any, private invoiceService: InvoiceService, private toastr: ToastrService) { 
    this.invoiceId = data.invoiceId;
  }

  ngOnInit(): void {
    this.updateInvoiceForm = new FormGroup({
      status: new FormControl('', [Validators.required])
    })
  }

  close(){
    this.dialogRef.close();
  }

  updateInvoice() {
    let invoice = {
      invoiceId: this.invoiceId,
      status: this.updateInvoiceForm.get('status')?.value
    }

    this.invoiceService.updateInvoice(invoice).subscribe(
      data => {
        this.toastr.success("Successfully updated invoice");
        this.close();
      },
      error => {
        this.toastr.error(error.error.errorMessage, "Error updating the invoice");
      })
  }

}
