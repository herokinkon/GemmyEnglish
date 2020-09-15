import { Classes } from '../../class-management/class-service/classes-model';

export interface Staff {
    id: number;
    fullName?: string;
    birthday?: Date;
    email?: string;
    facebook?: string;
    contactNumber?: string;
    salary: string;
    staffType: string;
    workOfStaff: string;
    classes: Classes[];
    bankAccount: string;
    bankName: string;
    bankBranch: string;
    ieltsScore: number;
    othersCertificate: string;
}

export class StaffInfo implements Staff {
    id: number;
    fullName?: string;
    birthday?: Date;
    email?: string;
    facebook?: string;
    contactNumber?: string;
    salary: string;
    staffType: string;
    workOfStaff: string;
    classes: Classes[];
    bankAccount: string;
    bankName: string;
    bankBranch: string;
    ieltsScore: number;
    othersCertificate: string;
    
}