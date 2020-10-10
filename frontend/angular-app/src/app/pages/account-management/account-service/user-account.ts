import { Staff } from '../../staff-management/staff-service/staff';

export class UserAccount {
    id: number;
    userName?: string;
    // Just for persistent
    password?: string;
    status?: boolean;
    roles?: Role;
    staff?: Staff;
}

export enum Role {
    Salesperson, Assistant, Teacher, Office, Academic, Admin
}
