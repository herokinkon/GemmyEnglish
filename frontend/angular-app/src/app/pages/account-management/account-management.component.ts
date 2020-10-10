import { Component, OnInit } from '@angular/core';
import { AccountServices } from './account-service/account.service';
import { UserAccount } from './account-service/user-account';
import { MatDialog } from '@angular/material/dialog';
import { AccountDetailComponent } from './account-detail/account-detail.component';

@Component({
  selector: 'app-account-management',
  templateUrl: './account-management.component.html',
  styleUrls: ['./account-management.component.css']
})
export class AccountManagementComponent implements OnInit {
  accounts: UserAccount[];
  selectedAccount: UserAccount;
  constructor(private accountService: AccountServices, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.accountService.getAccounts().subscribe(data => this.accounts = data);
  }

  onRowSelect(event: any) {
    const data = { ...event.data, isNew: false, title: 'Edit Account' };
    const dialog = this.dialog.open(AccountDetailComponent, { data });
    dialog.afterClosed().subscribe(() => this.ngOnInit());
    // const result = this.dialog.openDialog('Student Detail', StudentDetailComponent, { ...event.data });
    // result.subscribe(evt => this.updateTable(evt));
  }

  showDialogToAdd() {
    const data = { isNew: true, title: 'New Account', staff: {} };
    const dialog = this.dialog.open(AccountDetailComponent, { data });
    dialog.afterClosed().subscribe(() => this.ngOnInit());
    // const result = this.dialog.openDialog('New Student', StudentDetailComponent, {});
    // result.subscribe(evt => this.updateTable(evt));
  }

  showResetPwd() {

  }
}
