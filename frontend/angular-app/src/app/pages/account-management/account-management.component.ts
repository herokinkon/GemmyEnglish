import { Component, OnInit } from '@angular/core';
import { AccountServices } from './account-service/account.service';
import { UserAccount } from './account-service/user-account';

@Component({
  selector: 'app-account-management',
  templateUrl: './account-management.component.html',
  styleUrls: ['./account-management.component.css']
})
export class AccountManagementComponent implements OnInit {
  accounts: UserAccount[];
  selectedAccount: UserAccount;
  constructor(private accountService: AccountServices) { }

  ngOnInit(): void {
    this.accountService.getAccounts().subscribe(data => this.accounts = data);
  }

  onRowSelect(event: any) {
    // const result = this.dialog.openDialog('Student Detail', StudentDetailComponent, { ...event.data });
    // result.subscribe(evt => this.updateTable(evt));
  }

  showDialogToAdd() {
    // const result = this.dialog.openDialog('New Student', StudentDetailComponent, {});
    // result.subscribe(evt => this.updateTable(evt));
  }
}
