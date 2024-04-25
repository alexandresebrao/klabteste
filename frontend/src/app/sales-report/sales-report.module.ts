import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SalesReportComponent } from './sales-report.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatDialogModule } from '@angular/material/dialog';
import { MatMenuModule } from '@angular/material/menu';

const route = [{ component: SalesReportComponent, path: '' }]

@NgModule({
  declarations: [SalesReportComponent],
  imports: [CommonModule, RouterModule.forChild(route), HttpClientModule, FormsModule, MatMenuModule],
})
export class SalesReportModule { }
