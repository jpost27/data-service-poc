import { Competitors } from './Competitors';
import { Teams } from './Teams';
import { Seasons } from './Seasons';
import { Sports } from './Sports';
export interface Leagues {
    leagueId: number;
    sportId: number;
    abbreviatedName: string;
    fullName: string;
    sports?: Sports;
    seasons?: Seasons;
    competitors?: Competitors[];
    teams?: Teams[];
}
