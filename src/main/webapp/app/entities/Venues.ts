import { Events } from './Events';
import { Teams } from './Teams';
export interface Venues {
    venueId: number;
    capacity?: number;
    latitude?: number;
    longitude?: number;
    address?: string;
    city?: string;
    country?: string;
    name?: string;
    roofType?: string;
    state?: string;
    surface?: string;
    zip?: string;
    events?: Events[];
    teams?: Teams[];
}
